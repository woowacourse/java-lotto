package lotto;

import lotto.domain.AutoTicketsFactory;
import lotto.domain.ManualTicketsFactory;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.PurchasingAmount;
import lotto.domain.Ranks;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoApplication {
	public static void main(String[] args) {
		final Money inputMoney = new Money(InputView.inputPayment());
		final int manualLottoCount = Integer.parseInt(InputView.inputManualLottoCount());
		final Money auto = inputMoney.minus(Money.ticketPriceOf(manualLottoCount));

		PurchasingAmount amountOfAuto = new PurchasingAmount(auto.getMoney());

		final LottoTickets manualTickets = ManualTicketsFactory.create(InputView.inputManualNumbers(manualLottoCount));
		final LottoTickets autoTickets = AutoTicketsFactory.create(amountOfAuto);
		final LottoTickets allTickets = manualTickets.add(autoTickets);

		OutputView.printLottoAmount(manualTickets.size(), autoTickets.size());
		OutputView.printLottoState(allTickets.tickets());

		final WinningNumbers winningNumbers =
			new WinningNumbers(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

		final Ranks results = allTickets.getRanksBy(winningNumbers);

		OutputView.printResult(results);
		OutputView.printProfit(results.getTotalProfitComparedTo(inputMoney));
	}
}
