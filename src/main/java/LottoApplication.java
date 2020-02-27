import domain.*;
import domain.numberscontainer.BonusNumberDto;
import domain.numberscontainer.LottoNumbersDto;
import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            LottoNumbersDto lottoNumbersDto = new LottoNumbersDto(InputView.enterLastWeekWinningNumbers());
            BonusNumberDto bonusNumberDTO = new BonusNumberDto(InputView.enterBonusNumber());
            return new WinningNumbers(lottoNumbersDto, bonusNumberDTO);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterWinningNumbers();
        }
    }
}