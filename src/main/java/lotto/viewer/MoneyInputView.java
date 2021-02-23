package lotto.viewer;

import java.util.Scanner;
import lotto.domain.Money;

public class MoneyInputView {

    private static final String PURCAHSE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";

    final Scanner scanner;

    public MoneyInputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Money purchaseMoney() {
        System.out.println(PURCAHSE_MONEY_MESSAGE);
        int rawMoney = scanner.nextInt();
        Money money = new Money(rawMoney);
        scanner.nextLine();
        return money;
    }
}
