import domain.Money;
import domain.User;
import domain.numberscontainer.BonusNumberDTO;
import domain.numberscontainer.SixLottoNumbersDTO;
import domain.numberscontainer.WinningNumbers;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        User user = new User();
        user.buyTickets(enterMoney());

        OutputView.printNumberOfTickets(user.getTicketsSize());
        OutputView.printTickets(user.getTickets());

        WinningNumbers winningNumbers = enterWinningNumbers();
        OutputView.printLottoResults(user.confirmResult(winningNumbers));
        OutputView.printProfit(user.calculateProfit());
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