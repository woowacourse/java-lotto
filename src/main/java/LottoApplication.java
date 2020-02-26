import domain.*;
import domain.numberscontainer.BonusNumberDTO;
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
        final List<Ticket> tickets = LottoStore.createTickets(money.getTotalTicketSize(), manualTicketSize, parseDTO(manualTicketSize));
        OutputView.printNumberOfTickets(manualTicketSize, money.getTotalTicketSize() - manualTicketSize);
        OutputView.printTickets(tickets);

        WinningNumbers winningNumbers = enterWinningNumbers();
        Map<LottoResult, Integer> result = LottoResultMachine.confirmResult(tickets, winningNumbers);
        OutputView.printLottoResults(result);
        OutputView.printProfit(LottoProfit.ofProfit(result, money));
    }

    private static List<LottoNumbersDto> parseDTO(int manualTicketSize) {
        return InputView.enterManualTickets(manualTicketSize).stream()
                .map(LottoNumbersDto::new)
                .collect(Collectors.toList());
    }

    private static WinningNumbers enterWinningNumbers() {
        try {
            LottoNumbersDto lottoNumbersDto = new LottoNumbersDto(InputView.enterLastWeekWinningNumbers());
            BonusNumberDTO bonusNumberDTO = new BonusNumberDTO(InputView.enterBonusNumber());
            return new WinningNumbers(lottoNumbersDto, bonusNumberDTO);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterWinningNumbers();
        }
    }
}