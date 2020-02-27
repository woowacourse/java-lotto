package lotto.controller;

import static lotto.domain.LottoNumberParser.*;
import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import lotto.domain.LottoStore;
import lotto.domain.ManualLottoCount;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoMoney;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;

public class LottoController {
	public void run() {
		final LottoMoney inputLottoMoney = continuousInputMoney();
		final Lottos totalLottos = purchaseLotto(inputLottoMoney);
		printPurchasedLotto(totalLottos);

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

		printInputManualLottoMessage();
		final LottoStore lottoStore = new LottoStore();
		Lottos totalLottos = lottoStore.mergeManualAndAuto(manualLottoCount.getCount(),
			purchasedLottoCount - manualLottoCount.getCount());
		printPurchaseCompleteMessage(manualLottoCount.getCount(),
			purchasedLottoCount - manualLottoCount.getCount());

		return totalLottos;
	}
}