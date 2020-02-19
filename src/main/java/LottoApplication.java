import domain.LottoNumber;
import domain.Money;
import domain.factory.LottoNumberFactory;
import domain.numberscontainer.LottoNumbersDto;
import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = enterMoney();
        OutputView.printNumberOfTickets(money);
        int ticketSize = money.getNumberOfTickets();
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketSize; i++) {
            LottoNumbersDto lottoNumbersDto = LottoNumberFactory.generateRandomTicketDto();
            tickets.add(new Ticket(lottoNumbersDto));
        }
        OutputView.printTickets(tickets);

        WinningNumbers winningNumbers = enterWinningNumbers();

    }

    private static WinningNumbers enterWinningNumbers() {
        while (true) {
            try {
                Set<LottoNumber> lastWeekWinningNumbers = enterLastWeekWinningNumber();
                LottoNumber bonusNumber = enterBonusNumber();

                LottoNumbersDto lottoNumbersDto = LottoNumberFactory.generateFixedNumberDto(lastWeekWinningNumbers, bonusNumber);
                return new WinningNumbers(lottoNumbersDto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Money enterMoney() {
        while (true) {
            try {
                return InputView.enterMoney();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Set<LottoNumber> enterLastWeekWinningNumber() {
        while (true) {
            try {
                return InputView.enterLastWeekWinningNumbers();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static LottoNumber enterBonusNumber() {
        while (true) {
            try {
                return InputView.enterBonusNumber();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}