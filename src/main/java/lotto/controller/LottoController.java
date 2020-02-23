package lotto.controller;

import java.io.IOException;
import java.util.List;

import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.domain.WinningPrize;
import lotto.dto.LottoCountDto;
import lotto.dto.LottosDto;
import lotto.utils.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		Lottos lottos = buyLottos();
		OutputView.printLottos(LottosDto.from(lottos));
		List<WinningPrize> winningPrizes = lottos.findAllLottoPrizes(receiveWinningNumber());
		OutputView.printLottoResult(winningPrizes);
	}

	private static int readBonusNumber() {
		try {
			InputView.printInsertBonusNumber();
			return InputUtil.inputBonusNumber();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongBonusNumberInput(e);
			return readBonusNumber();
		}
	}

	private static Lottos buyLottos() {
		try {
			LottoMachine lottoMachine = LottoMachine.getInstance();
			LottoCountDto lottoCountDto = new LottoCountDto(readMoney());

			OutputView.printLottoCount(lottoCountDto.getLottoCount());
			return new Lottos(lottoMachine.makeRandomLottos(lottoCountDto.getLottoCount()));
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return buyLottos();
		}
	}

	public static int readMoney() {
		try {
			InputView.printInsertMoney();
			return InputUtil.inputMoney();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongMoneyInput(e);
			return readMoney();
		}
	}

	public static WinningLotto receiveWinningNumber() {
		try {
			return new WinningLotto(readWinningNumber(), readBonusNumber());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return receiveWinningNumber();
		}
	}

	public static List<String> readWinningNumber() {
		try {
			InputView.printInsertWinningNumber();
			return InputUtil.inputWinningNumber();
		} catch (IOException e) {
			e.printStackTrace();
			return readWinningNumber();
		}
	}
}
