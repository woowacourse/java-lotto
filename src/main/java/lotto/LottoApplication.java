package lotto;

import lotto.domain.Ball;
import lotto.domain.LottoCount;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.factory.LottosFactory;
import lotto.domain.factory.RandomLottoFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			int inputMoney = InputView.inputMoney();
			Money money = new Money(inputMoney);
			LottoCount count = money.findLottoTicketCount();
			OutputView.printLottoCount(count);

			LottosFactory lottosFactory = new LottosFactory(new RandomLottoFactory());
			LottoTickets lottos = lottosFactory.createLottosByCount(count);
			OutputView.printLottos(lottos);

			LottoTicket winningLottoTicket = LottoTicket.of(InputView.inputWinningLotto());
			Ball bonusBall = Ball.of(InputView.inputWinningBonusBall());
			WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusBall);
			LottoResult result = winningLotto.getResult(lottos);
			OutputView.printStatistics(result, money);
		} catch (RuntimeException ex) {
			OutputView.printExceptionMessage(ex.getMessage());
		}
	}
}
