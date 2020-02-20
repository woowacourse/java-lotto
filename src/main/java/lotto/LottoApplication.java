package lotto;

import lotto.domain.AutoLottosGenerator;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.LottoStatistics;
import lotto.domain.Lottos;
import lotto.domain.LottosGenerator;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(InputView.inputPurchaseMoney());
		LottosGenerator lottosGenerator = new AutoLottosGenerator();
		int buyCount = lottoPurchaseMoney.getBuyCount();
		Lottos lottos = lottosGenerator.generate(buyCount);
		OutputView.printBuyCount(buyCount);
		OutputView.printLottos(lottos);
		WinningLotto winningLotto = new WinningLotto(Lotto.of(1, 2, 3, 4, 5, 6), LottoNumber.of(7));
		LottoStatistics lottoStatistics = new LottoStatistics(lottoPurchaseMoney, lottos.match(winningLotto));
		long profitRate = lottoStatistics.getProfitRate();
		System.out.println(profitRate);
	}
}
