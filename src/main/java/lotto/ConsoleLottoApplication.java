package lotto;

import java.util.function.Function;
import java.util.stream.Collectors;

import lotto.domain.CreateRandomTicketsStrategy;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketFactory;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.ProfitCalculator;
import lotto.domain.PurchasingAmount;
import lotto.domain.Rank;
import lotto.domain.Ranks;
import lotto.domain.TicketComparator;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoApplication {
	public static void main(String[] args) {
		final Money inputMoney = new Money(InputView.inputPayment());
		PurchasingAmount purchasingAmount = new PurchasingAmount(inputMoney.getLottoMoneyValue());

		final LottoTickets lottoTickets =
			LottoTicketFactory.create(purchasingAmount, new CreateRandomTicketsStrategy());

		OutputView.printLottoAmount(lottoTickets);
		OutputView.printLottoState(lottoTickets);

		final WinningNumbers winningNumbers =
			new WinningNumbers(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

		final Ranks results = new Ranks(
			lottoTickets.stream()
				.map(getRankBy(winningNumbers))
				.collect(Collectors.toList())
		);

		OutputView.printResult(results);
		OutputView.printProfit(ProfitCalculator.calculate(inputMoney, results));
	}

	private static Function<LottoTicket, Rank> getRankBy(WinningNumbers winningNumbers) {
		return lottoTicket -> Rank.of(
			TicketComparator.getMatchCount(lottoTicket, winningNumbers),
			TicketComparator.isBonusNotMatch(lottoTicket, winningNumbers)
		);
	}
}
