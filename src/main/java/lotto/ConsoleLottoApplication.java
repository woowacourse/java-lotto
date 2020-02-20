package lotto;

import java.util.stream.Collectors;

import lotto.domain.CreateRandomTicketsStrategy;
import lotto.domain.LottoTicketFactory;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.PurchasingAmount;
import lotto.domain.Rank;
import lotto.domain.Ranks;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoApplication {
	public static void main(String[] args) {
		final Money inputMoney = new Money(InputView.inputPayment());
		PurchasingAmount purchasingAmount = new PurchasingAmount(inputMoney.getLottoMoneyValue());

		final LottoTickets lottoTickets =
			LottoTicketFactory.create(purchasingAmount, new CreateRandomTicketsStrategy());
		OutputView.printLottoState(lottoTickets);

		final WinningNumbers winningNumbers =
			new WinningNumbers(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

		final Ranks prizeResults =
			new Ranks(
				lottoTickets.stream()
					.map(lottoTicket -> Rank.of(lottoTicket, winningNumbers))
					.collect(Collectors.toList())
			);

		OutputView.printResult(prizeResults);
	}
}
