import domain.*;
import domain.numberscontainer.BonusNumberDTO;
import domain.numberscontainer.SixLottoNumbersDTO;
import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = enterMoney();
        List<Ticket> tickets = LottoStore.generateTickets(money.getNumberOfTickets());

        OutputView.printNumberOfTickets(tickets.size());
        OutputView.printTickets(tickets);

        WinningNumbers winningNumbers = enterWinningNumbers();
        Map<LottoResult, Integer> result = LottoResultMachine.confirmResult(tickets, winningNumbers);
        OutputView.printLottoResults(result);
        OutputView.printProfit(LottoProfit.ofProfit(result, money));
    }

    private static Money enterMoney() {
        try {
            return InputView.enterMoney();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return enterMoney();
        }
    }

    private static WinningNumbers enterWinningNumbers() {
        try {
            SixLottoNumbersDTO sixLottoNumbersDTO = InputView.enterLastWeekWinningNumbers();
            BonusNumberDTO bonusNumberDTO = InputView.enterBonusNumber();
            return new WinningNumbers(sixLottoNumbersDTO, bonusNumberDTO);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterWinningNumbers();
        }
    }
}