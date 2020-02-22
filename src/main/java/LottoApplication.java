import domain.Money;
import domain.User;
import domain.lottonumber.WinningNumbers;
import domain.result.LottoResult;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        User user = new User();

        user.buyTickets(enterMoney());
        printTicketInformation(user);

        WinningNumbers winningNumbers = enterWinningNumbers();

        printResult(user, winningNumbers);
    }

    private static Money enterMoney() {
        try {
            return InputView.enterMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private static void printTicketInformation(User user) {
        OutputView.printNumberOfTickets(user.getLottoTickets().size());
        OutputView.printTickets(user.getLottoTickets());
    }

    private static WinningNumbers enterWinningNumbers() {
        try {
            return new WinningNumbers(InputView.enterWinningNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private static void printResult(User user, WinningNumbers winningNumbers) {
        LottoResult lottoResult = user.confirmResult(winningNumbers);
        OutputView.printLottoResults(lottoResult);
        OutputView.printProfit(lottoResult.calculateProfit(user.getSpentMoney()));
    }
}