package lotto;

import lotto.domain.LottoBuyCount;
import lotto.domain.LottoFactory;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoStore;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(InputView.inputPurchaseMoney());
		LottoBuyCount manualBuyCount = new LottoBuyCount(lottoPurchaseMoney, InputView.inputManualLottoCount());
		LottoBuyCount autoBuyCount = new LottoBuyCount(lottoPurchaseMoney);
		Lottos lottos = new Lottos(LottoStore.buyAutoAndManual(autoBuyCount, manualBuyCount,
				InputView.inputManualLotto(manualBuyCount.getValue())));
		OutputView.printBuyLottos(manualBuyCount.getValue(), autoBuyCount.getValue(), lottos);
		WinningLotto winningLotto = new WinningLotto(LottoFactory.create(InputView.inputWinningLotto()),
				LottoNumber.of(InputView.inputWinningLottoBonus()));
		LottoStatistics lottoStatistics = new LottoStatistics(lottoPurchaseMoney, lottos.match(winningLotto));
		OutputView.printStatistics(lottoStatistics);
	}
}
