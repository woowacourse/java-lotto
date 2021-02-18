package lotto.view;

import java.util.Scanner;
import lotto.Money;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Money inputMoney() {
        return new Money(scanner.nextLine());
    }
}
