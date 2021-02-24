package lotto.view;

import java.util.Scanner;
import lotto.Money;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.ManualLotto;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView(Scanner scanner){

    }

    public static Money inputMoney() {
        return new Money(scanner.nextLine());
    }

    public static String inputWinningNumbers() {
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        return scanner.nextLine();
    }

    public static ManualLotto inputNumManualLotto() {
        return new ManualLotto(scanner.nextLine());
    }

    public static String inputManualLottoNumber() {
        return scanner.nextLine();
    }
}
