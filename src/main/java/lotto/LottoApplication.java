package lotto;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.LottosFactory;
import lotto.domain.Money;
import lotto.domain.RandomLottoFactory;
import lotto.domain.TotalResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		int inputMoney = InputView.inputMoney();
		Money money = new Money(inputMoney);
		LottosFactory lottosFactory = new LottosFactory(new RandomLottoFactory());
		int countA = money.getCount().getLottoCount();
		Lottos lottos = lottosFactory.createLottosByCount(countA);
		OutputView.printLottoCount(lottos.getCount());
		OutputView.printLottos(lottos);

		String rawWinningLotto = InputView.inputWinningLotto();
		int rawWinningBall = InputView.inputWinningBonusBall();

		Lotto lotto = Lotto.of(rawWinningLotto);

		WinningLotto winningLotto = new WinningLotto(lotto, Ball.of(rawWinningBall));
		TotalResult result = winningLotto.getResult(lottos);
		OutputView.printStatistics(result);
	}
}
