package lotto.controller;

import lotto.domain.*;
import lotto.dto.LottoCountDto;
import lotto.exception.LottoException;
import lotto.utils.InputUtil;
import lotto.view.OutputView;

import java.io.IOException;
import java.util.List;

public class LottoController {
	public static void run() {
		Lottos lottos = buyLottos();
		OutputView.printLottos(lottos.makeLottoDtos());
		List<WinningPrize> winningPrizes = lottos.findAllLottoPrizes(receiveWinningNumber());
		LottoResult lottoResult = new LottoResult(winningPrizes);
		OutputView.printLottoResult(lottoResult.getWinningInformation());
		OutputView.printEarningRate(lottoResult.calculateEarningRate());
	}

	private static int readBonusNumber() {
		try {
			return InputUtil.inputBonusNumber();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongBonusNumberInput();
			return readBonusNumber();
		}
	}

	private static Lottos buyLottos() {
		try {
			LottoMachine lottoMachine = new LottoMachine();
			LottoCountDto lottoCountDto = new LottoCountDto(readMoney());

			OutputView.printLottoCount(lottoCountDto.getLottoCount());
			return new Lottos(lottoMachine.makeRandomLottos(lottoCountDto));
		} catch (IllegalArgumentException | LottoException e) {
			OutputView.printExceptionMessage(e);
			return buyLottos();
		}
	}

	public static int readMoney() {
		try {
			return InputUtil.inputMoney();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongMoneyInput();
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
			return InputUtil.inputWinningNumber();
		} catch (IOException e) {
			e.printStackTrace();
			return readWinningNumber();
		}
	}
}
