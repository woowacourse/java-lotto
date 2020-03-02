import domain.*;
import domain.numberscontainer.*;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class LottoApplication {
    public static void main(String[] args) {
        final Money money = new Money(InputView.enterMoney());
        final int totalTicketSize = Tickets.getTotalTicketSize(money);
        final int manualTicketSize = Integer.parseInt(InputView.enterManualTicketSize());
        final Tickets tickets = Tickets.createTickets(totalTicketSize, manualTicketSize, InputView.enterManualTickets(manualTicketSize));

        OutputView.printNumberOfTickets(manualTicketSize, totalTicketSize - manualTicketSize);
        OutputView.printTickets(tickets);

        final WinningNumbers winningNumbers = new WinningNumbers(InputView.enterLastWeekWinningNumbers(), Integer.parseInt(InputView.enterBonusNumber()));
        final Map<LottoResult, Integer> result = LottoResultMachine.calculateResult(tickets, winningNumbers);
        OutputView.printLottoResults(result);
        OutputView.printProfit(LottoProfit.ofProfit(result, money));
    }
}