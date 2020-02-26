package lotto.controller;

import static lotto.domain.LottoNumberParser.*;
import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import lotto.domain.LottoStore;
import lotto.domain.ManualLottoCount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningResult;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoMoney;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;

public class LottoController {
	public void run() {
		final LottoMoney inputLottoMoney = continuousInputMoney();
		final Lottos totalLottos = purchaseLotto(inputLottoMoney);

		final Lotto winningLottoNumber = new Lotto(parseToLottoNumberSet(inputWinningLottoNumber()));
		final LottoNumber bonusLottoNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		printStatisticsMessage();

		final WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonusLottoNumber);
		final WinningResult winningResult = winningLotto.getWinningResult(totalLottos);
		printWinningResult(winningResult.exceptMiss());
		printWinningRatio(winningResult.getWinningRatio(inputLottoMoney));
	}

	private Lottos purchaseLotto(LottoMoney inputLottoMoney) {
		final int purchasedLottoCount = inputLottoMoney.getPurchasedLottoCount();
		final ManualLottoCount manualLottoCount = new ManualLottoCount(inputManualLottoCount(), purchasedLottoCount);
		final int autoLottoCount = purchasedLottoCount - manualLottoCount.getCount();

		printInputManualLottoMessage();
		final Lottos totalLottos = mergeManualAndAuto(autoLottoCount, manualLottoCount.getCount());

		printPurchaseCompleteMessage(manualLottoCount.getCount(), autoLottoCount);
		printPurchasedLotto(totalLottos);
		return totalLottos;
	}

	private Lottos mergeManualAndAuto(int manualLottoCount, int autoLottoCount) {
		LottoStore lottoStore = new LottoStore();
		Lottos manualLottos = lottoStore.purchaseManualLotto(manualLottoCount);
		Lottos autoLottos = lottoStore.purchaseAutoLotto(autoLottoCount);
		return Lottos.merge(manualLottos, autoLottos);
	}
}
