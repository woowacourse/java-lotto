package lotto.controller;

import lotto.domain.*;
import lotto.exception.LottoCountException;
import lotto.exception.LottoException;
import lotto.exception.LottosException;
import lotto.exception.WinningLottoException;
import lotto.utils.InputUtil;
import lotto.view.OutputView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
		} catch (IllegalArgumentException | LottoCountException | LottoException | LottosException e) {
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

	private static Lotto readLottoNumber(List<String> lottoNumberString) {
		return new Lotto(lottoNumberString.stream()
				.map(Integer::parseInt)
				.map(LottoMachine.getInstance()::pickBall)
				.collect(Collectors.toList()));
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
			return new WinningLotto(readLottoNumber(InputUtil.inputLottoNumber()), readBonusNumber());
		} catch (IllegalArgumentException | LottoException | WinningLottoException | IOException e) {
			OutputView.printExceptionMessage(e);
			return readWinningNumber();
		}
	}
}
