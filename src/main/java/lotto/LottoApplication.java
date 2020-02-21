package lotto;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
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
        LottoCount count = getCountByMoney();
        OutputView.printLottoCount(count);

        LottosFactory lottosFactory = new LottosFactory(new RandomLottoFactory());
        Lottos lottos = lottosFactory.createLottosByCount(count);
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = getWinningLotto();
        TotalResult result = winningLotto.getResult(lottos);
        OutputView.printStatistics(result);
    }

    private static LottoCount getCountByMoney() {
    	while(true) {
			try {
				int inputMoney = InputView.inputMoney();
				Money money = new Money(inputMoney);
				return money.getCount();
			} catch (RuntimeException re) {
				OutputView.printExceptionMessage(re.getMessage());
			}
		}
    }

    private static WinningLotto getWinningLotto() {
    	while(true) {
			try {
				Lotto winningLotto = Lotto.of(InputView.inputWinningLotto());
				Ball bonusBall = Ball.of(InputView.inputWinningBonusBall());
				return new WinningLotto(winningLotto, bonusBall);
			} catch (RuntimeException re) {
				OutputView.printExceptionMessage(re.getMessage());
			}
		}
    }
}
