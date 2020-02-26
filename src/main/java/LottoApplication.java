import domain.Money;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.WinningNumbers;
import domain.lottostore.RandomLottoStore;
import domain.result.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = enterMoney();
        List<LottoTicket> randomTickets = new RandomLottoStore().generateTickets(money);

        printTickets(randomTickets);

        WinningNumbers winningNumbers = enterWinningNumbers();
        LottoResult lottoResult = LottoResult.confirmResult(randomTickets, winningNumbers);

        printResult(lottoResult, money);
    }

    private static Money enterMoney() {
        try {
            return InputView.enterMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void printTickets(List<LottoTicket> tickets) {
        OutputView.printNumberOfTickets(tickets.size());
        OutputView.printTickets(tickets);
    }

    private static WinningNumbers enterWinningNumbers() {
        try {
            return new WinningNumbers(InputView.enterWinningNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void printResult(LottoResult lottoResult, Money money) {
        OutputView.printLottoResults(lottoResult);
        OutputView.printProfit(lottoResult.calculateProfit(money));
    }
}