package lotto.controller;

import lotto.domain.*;
import lotto.exception.LottoException;
import lotto.exception.WinningLottoException;
import lotto.utils.InputUtil;
import lotto.view.OutputView;

import java.io.IOException;
import java.util.ArrayList;
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
			LottoCount lottoCount = new LottoCount(readMoney(), readManualLottoCount());
			List<Lotto> lottos = new ArrayList<>();
			lottos.addAll(buyManualLottos(lottoCount));
			lottos.addAll(LottoMachine.getInstance().makeRandomLottos(lottoCount));
			OutputView.printLottoCount(lottoCount.getManualLottoCount(), lottoCount.getAutoLottoCount());
			return new Lottos(lottos);
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
			return buyLottos();
		}
	}

	private static List<Lotto> buyManualLottos(LottoCount lottoCount) {
		List<Lotto> manualLottos = new ArrayList<>();
		OutputView.printManualInputGuide();
		for (int i = 0; i < lottoCount.getManualLottoCount(); i++) {
			manualLottos.add(readLottoNumber());
		}
		return manualLottos;
	}

	private static int readMoney() {
		try {
			return InputUtil.inputMoney();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongIntegerInput();
			return readMoney();
		}
	}

	private static int readManualLottoCount() {
		try {
			return InputUtil.inputManualLottoCount();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongIntegerInput();
			return readManualLottoCount();
		}
	}

	private static Lotto readLottoNumber() {
		try {
			return new Lotto(LottoMachine.getInstance()
					.pickDedicatedBalls(InputUtil.inputLottoNumber()
							.stream()
							.map(Integer::parseInt)
							.collect(Collectors.toList())));
		} catch (LottoException | IOException e) {
			OutputView.printExceptionMessage(e);
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
			OutputView.printWinningNumberInputGuide();
			return new WinningLotto(readLottoNumber(), readBonusNumber());
		} catch (IllegalArgumentException | LottoException | WinningLottoException e) {
			OutputView.printExceptionMessage(e);
			return readWinningNumber();
		}
	}
}
