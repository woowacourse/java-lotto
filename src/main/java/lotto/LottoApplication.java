package lotto;

import lotto.domain.Ball;
import lotto.domain.LottoCount;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketFactory;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.generator.LottoTicketsGenerator;
import lotto.domain.generator.RandomLottoTicketGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			int inputMoney = InputView.inputMoney();
			Money money = Money.of(inputMoney);
			LottoCount count = money.findLottoTicketCount();
			OutputView.printLottoCount(count);

			LottoTicketsGenerator lottoTicketsGenerator = new LottoTicketsGenerator(new RandomLottoTicketGenerator());
			LottoTickets lottos = lottoTicketsGenerator.createLottosByCount(count);
			OutputView.printLottos(lottos);

			LottoTicket winningLottoTicket = LottoTicketFactory.of(InputView.inputWinningLotto());
			Ball bonusBall = Ball.of(InputView.inputWinningBonusBall());
			WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusBall);
			LottoResult result = winningLotto.getResult(lottos);
			OutputView.printStatistics(result, money);
		} catch (RuntimeException ex) {
			OutputView.printExceptionMessage(ex.getMessage());
		}
	}
}
