import domain.Money;
import view.InputView;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = enterMoney();
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
}