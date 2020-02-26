package lotto.controller;

import static lotto.domain.LottoNumberParser.*;
import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import lotto.domain.LottoStore;
import lotto.domain.ManualLottoCount;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;
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
		final WinningResult result = winningLotto.getWinningResult(totalLottos);
		printWinningResult(result);
		printWinningRatio(result, inputLottoMoney);
	}

	private Lottos purchaseLotto(LottoMoney lottoMoney) {
		final int purchasedLottoCount = lottoMoney.getPurchasedLottoCount();
		final ManualLottoCount manualLottoCount = new ManualLottoCount(inputManualLottoCount(), purchasedLottoCount);

		final int manualCount = manualLottoCount.getCount();
		final int autoCount = purchasedLottoCount - manualCount;

		final Lottos totalLottos = mergeManualAndAuto(manualCount, autoCount);

		printPurchaseCompleteMessage(manualCount, autoCount);
		printPurchasedLotto(totalLottos);
		return totalLottos;
	}

	private Lottos mergeManualAndAuto(int manualCount, int autoCount) {
		final LottoStore lottoStore = new LottoStore();

		final Lottos autoLottos = lottoStore.purchaseAutoLotto(autoCount);
		if (manualCount == 0) {
			return autoLottos;
		}

		printInputManualLottoMessage();
		final Lottos manualLottos = lottoStore.purchaseManualLotto(manualCount);

		return Lottos.merge(manualLottos, autoLottos);
	}
}
