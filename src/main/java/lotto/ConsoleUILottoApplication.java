package lotto;

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

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		LottoMoney inputLottoMoney = continuousInputMoney();
		ManualLottoCount manualLottoCount = new ManualLottoCount(inputManualLottoCount(), inputLottoMoney);

		printManualLottoMessage();
		LottoStore lottoStore = new LottoStore();
		Lottos manualLottos = lottoStore.purchaseManualLotto(manualLottoCount.getCount());
		Lottos autoLottos = lottoStore.purchaseAutoLotto(
			inputLottoMoney.getPurchasedLottoCount() - manualLottoCount.getCount());

		printPurchaseCompleteMessage(manualLottoCount.getCount(),
			inputLottoMoney.getPurchasedLottoCount() - manualLottoCount.getCount());
		printPurchasedLotto(manualLottos);
		printPurchasedLotto(autoLottos);

		Lotto winningLottoNumber = new Lotto(parseToLottoNumberSet(inputWinningLottoNumber()));
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		printStatisticsMessage();

		WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonusLottoNumber);
		Map<LottoRank, Integer> winningLottoCount = winningLotto.getWinningLottoCount(autoLottos);
		printWinningResult(winningLottoCount);

		int winningRatio = winningLotto.getWinningRatio(winningLottoCount, inputLottoMoney);
		printWinningRatio(winningRatio);
	}
}
