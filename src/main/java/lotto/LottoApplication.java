package lotto;

import lotto.domain.AutoLottosGenerator;
import lotto.domain.LottoFactory;
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
		int buyCount = lottoPurchaseMoney.getBuyCount();
		Lottos lottos = new AutoLottosGenerator().generate(buyCount);
		OutputView.printBuyCount(buyCount);
		OutputView.printLottos(lottos);
		WinningLotto winningLotto = new WinningLotto(LottoFactory.create(InputView.inputWinningLotto()),
				LottoNumber.of(InputView.inputWinningLottoBonus()));
		LottoStatistics lottoStatistics = new LottoStatistics(lottoPurchaseMoney, lottos.match(winningLotto));
		OutputView.printStatistics(lottoStatistics);
	}
}
