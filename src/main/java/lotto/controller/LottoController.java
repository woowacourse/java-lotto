package lotto.controller;

import lotto.domain.*;
import lotto.exception.LottoException;
import lotto.utils.InputUtil;
import lotto.view.OutputView;

import java.io.IOException;
import java.util.List;

public class LottoController {
	public static void run() {
		Lottos lottos = buyLottos();
		OutputView.printLottos(lottos.makeLottoDtos());
		List<WinningPrize> winningPrizes = lottos.findAllLottoPrizes(readWinningNumber());
		LottoResult lottoResult = new LottoResult(winningPrizes);
		OutputView.printLottoResult(lottoResult.getWinningInformation());
		OutputView.printEarningRate(lottoResult.calculateEarningRate());
	}

	private static Lottos buyLottos() {
		try {
			LottoCount lottoCount = new LottoCount(readMoney());

			OutputView.printLottoCount(lottoCount.getLottoCount());
			return new Lottos(LottoMachine.getInstance().makeRandomLottos(lottoCount.getLottoCount()));
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

	public static List<String> readLottoNumber() {
		try {
			return InputUtil.inputLottoNumber();
		} catch (IOException e) {
			e.printStackTrace();
			return readLottoNumber();
		}
	}

	private static LottoNumber readBonusNumber() {
		try {
			return LottoMachine.getInstance().pickBall(InputUtil.inputBonusNumber());
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongBonusNumberInput();
			return readBonusNumber();
		}
	}

	public static WinningLotto readWinningNumber() {
		try {
			return new WinningLotto(readLottoNumber(), readBonusNumber());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return readWinningNumber();
		}
	}
}
