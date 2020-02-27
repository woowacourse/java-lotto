import domain.*;
import domain.numberscontainer.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoApplication {
    public static void main(String[] args) {
        final Money money = new Money(InputView.enterMoney());
        final int manualTicketSize = Integer.parseInt(InputView.enterManualTicketSize());
        final List<Ticket> tickets = LottoStore.createTickets(money, manualTicketSize, InputView.enterManualTickets(manualTicketSize));

        OutputView.printNumberOfTickets(manualTicketSize, tickets.size() - manualTicketSize);
        OutputView.printTickets(tickets);

        WinningNumbers winningNumbers = enterWinningNumbers();
        final Map<LottoResult, Integer> result = LottoResultMachine.calculateResult(tickets, winningNumbers);
        OutputView.printLottoResults(result);
        OutputView.printProfit(LottoProfit.ofProfit(result, money));
    }

    private static WinningNumbers enterWinningNumbers() {
        try {
            LottoNumbersDto lottoNumbersDto = LottoNumbersDtoAssembler.assemble(InputView.enterLastWeekWinningNumbers());
            BonusNumberDto bonusNumberDTO = BonusNumberDtoAssembler.assemble(InputView.enterBonusNumber());
            return new WinningNumbers(lottoNumbersDto, bonusNumberDTO);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterWinningNumbers();
        }
    }
}