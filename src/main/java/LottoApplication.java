import domain.LottoNumber;
import domain.Money;
import view.InputView;

import java.util.Set;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = enterMoney();
        Set<LottoNumber> lastWeekWinningNumbers = enterLastWeekWinningNumber();
        LottoNumber bonusNumber = enterBonusNumber();

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