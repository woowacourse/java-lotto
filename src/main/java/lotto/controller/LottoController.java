package lotto.controller;

import static lotto.domain.LottoNumberParser.*;
import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoStore;
import lotto.domain.Lottos;
import lotto.domain.ManualLottoCount;
import lotto.domain.WinningLotto;

public class LottoController {
	public void run() {
		final LottoMoney inputLottoMoney = continuousInputMoney();
		final int purchasedLottoCount = inputLottoMoney.getPurchasedLottoCount();

		final ManualLottoCount manualLottoCount = new ManualLottoCount(inputManualLottoCount(), purchasedLottoCount);
		final int autoLottoCount = purchasedLottoCount - manualLottoCount.getCount();

		printInputManualLottoMessage();
		final Lottos totalLottos = mergeAutoAndManual(autoLottoCount, manualLottoCount.getCount());

		printPurchaseCompleteMessage(manualLottoCount.getCount(), autoLottoCount);
		printPurchasedLotto(totalLottos);

		final Lotto winningLottoNumber = new Lotto(parseToLottoNumberSet(inputWinningLottoNumber()));
		final LottoNumber bonusLottoNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		printStatisticsMessage();

		final WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonusLottoNumber);
		final Map<LottoRank, Integer> winningLottoCount = winningLotto.getWinningLottoCount(totalLottos);
		printWinningResult(winningLottoCount);

		final int winningRatio = winningLotto.getWinningRatio(winningLottoCount, inputLottoMoney);
		printWinningRatio(winningRatio);
	}

	private Lottos mergeAutoAndManual(int autoLottoCount, int manualLottoCount) {
		LottoStore lottoStore = new LottoStore();
		Lottos manualLottos = lottoStore.purchaseManualLotto(manualLottoCount);
		Lottos autoLottos = lottoStore.purchaseAutoLotto(autoLottoCount);
		return Lottos.merge(manualLottos, autoLottos);
	}
}
