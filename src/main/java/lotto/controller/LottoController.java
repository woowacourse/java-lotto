package lotto.controller;

import lotto.domain.*;
import lotto.exception.LottoException;
import lotto.exception.WinningLottoException;
import lotto.utils.InputUtil;
import lotto.view.OutputView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lotto.utils.InputUtil.inputLottoNumber;

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
			LottoCount lottoCount = new LottoCount(InputUtil.inputMoney(), InputUtil.inputManualLottoCount());
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
			manualLottos.add(generateLotto(inputLottoNumber()));
		}
		return manualLottos;
	}

	private static Lotto generateLotto(List<Integer> lottoNumber) {
		try {
			return LottoMachine.getInstance()
					.pickDedicatedBalls(lottoNumber);
		} catch (LottoException e) {
			OutputView.printExceptionMessage(e);
			return generateLotto(inputLottoNumber());
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

	private static WinningLotto readWinningNumber() {
		try {
			OutputView.printWinningNumberInputGuide();
			return new WinningLotto(generateLotto(inputLottoNumber()), readBonusNumber());
		} catch (IllegalArgumentException | LottoException | WinningLottoException e) {
			OutputView.printExceptionMessage(e);
			return readWinningNumber();
		}
	}
}
