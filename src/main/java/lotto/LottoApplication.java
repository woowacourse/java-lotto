package lotto;

import java.util.Arrays;

import lotto.domain.result.TotalResult;
import lotto.domain.result.WinningLotto;
import lotto.domain.ticket.AutoLottoTicketsFactory;
import lotto.domain.ticket.CompositeLottoTicketsFactory;
import lotto.domain.ticket.LottoBall;
import lotto.domain.ticket.LottoCount;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.ManualLottoTicketsFactory;
import lotto.domain.ticket.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			Money money = Money.valueOf(InputView.inputMoney());
			LottoCount totalCount = money.calculatePurchaseCount();
			LottoCount manualCount = LottoCount.valueOf(InputView.inputManualTicketSize());
			LottoCount autoCount = totalCount.minus(manualCount);
			LottoTickets lottos = new CompositeLottoTicketsFactory(Arrays.asList(
				new ManualLottoTicketsFactory(InputView.inputManualLotto(manualCount.getCount()), manualCount),
				new AutoLottoTicketsFactory(autoCount)
			)).create();
			OutputView.printLottoCount(totalCount);
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
