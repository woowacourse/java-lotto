package lotto;

import lotto.domain.LottoBall;
import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoTicketsGenerator;
import lotto.domain.Money;
import lotto.domain.RandomLottoTicketGenerator;
import lotto.domain.TotalResult;
import lotto.domain.WinningLotto;
import lotto.util.StringUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			Money money = Money.valueOf(InputView.inputMoney());
			LottoCount count = money.calculatePurchaseCount();
			OutputView.printLottoCount(count);

			LottoTicketsGenerator autoLottoTicketsGenerator = new LottoTicketsGenerator(new RandomLottoTicketGenerator());
			LottoTickets lottos = autoLottoTicketsGenerator.createLottosByCount(count);
			OutputView.printLottos(lottos);

			LottoTicket winningLottoTicket = LottoTicket.of(InputView.inputWinningLotto());
			LottoBall bonusBall = LottoBall.valueOf(InputView.inputWinningBonusBall());
			WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusBall);
			TotalResult totalResult = new TotalResult(winningLotto.calculateResult(lottos), money);
			OutputView.printStatistics(totalResult);
		} catch (RuntimeException ex) {
			OutputView.printExceptionMessage(ex.getMessage());
		}
	}
}
