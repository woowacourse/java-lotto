package lotto;

import static lotto.domain.LottoNumberParser.*;
import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import java.util.Map;

import lotto.domain.AutoLottoPurchaseStrategy;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchaseStrategy;
import lotto.domain.LottoRank;
import lotto.domain.LottoStore;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		LottoMoney inputLottoMoney = continuousInputMoney();
		printPurchaseCompleteMessage(inputLottoMoney);

		LottoPurchaseStrategy lottoPurchaseStrategy = new AutoLottoPurchaseStrategy();
		LottoStore lottoStore = new LottoStore(lottoPurchaseStrategy);
		Lottos lottos = lottoStore.purchaseLotto(inputLottoMoney);
		printPurchasedAutoLotto(lottos);

		Lotto winningLottoNumber = new Lotto(parseToLottoNumberSet(inputWinningLottoNumber()));
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		printStatisticsMessage();

		WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonusLottoNumber);
		Map<LottoRank, Integer> winningLottoCount = winningLotto.getWinningLottoCount(lottos);
		printWinningResult(winningLottoCount);

		int winningRatio = winningLotto.getWinningRatio(winningLottoCount, inputLottoMoney);
		printWinningRatio(winningRatio);
	}
}
