package lotto;

import java.util.Arrays;

import lotto.domain.CompositeLottoTicketsGenerator;
import lotto.domain.LottoBall;
import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.ManualLottoTicketGenerator;
import lotto.domain.Money;
import lotto.domain.RandomLottoTicketGenerator;
import lotto.domain.TotalResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			Money money = Money.valueOf(InputView.inputMoney());
			LottoCount totalCount = money.calculatePurchaseCount();
			LottoCount manualCount = LottoCount.valueOf(InputView.inputManualTicketSize());
			LottoCount autoCount = totalCount.minus(manualCount);
			//OutputView.printLottoCount(totalCount);
			CompositeLottoTicketsGenerator compositeLottoTicketsGenerator = new CompositeLottoTicketsGenerator(
				(Arrays.asList(new ManualLottoTicketGenerator(), new RandomLottoTicketGenerator())),
				(Arrays.asList(manualCount, autoCount))
			);

			LottoTickets lottos = compositeLottoTicketsGenerator.create();
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
