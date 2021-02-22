package lotto.view;

import java.util.Scanner;
import lotto.Money;
import lotto.domain.lotto.Lotto;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Money inputMoney() {
        return new Money(scanner.nextLine());
    }

    public String inputWinningNumbers() {
        return scanner.nextLine();
    }

    public String inputBonusNumber() {
        return scanner.nextLine();
    }
}
