package lotto.viewer;

import java.util.Scanner;
import lotto.domain.Money;
import lotto.exception.LottoPiecesException;

public class MoneyInputView {

    private static final String PURCAHSE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";

    final Scanner scanner;

    public MoneyInputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Money purchaseMoney() {
        System.out.println(PURCAHSE_MONEY_MESSAGE);
        Money money;
        try {
            int rawMoney = scanner.nextInt();
            money = new Money(rawMoney);
        } catch (LottoPiecesException lottoPiecesException) {
            money = purchaseMoney();
        }
        scanner.nextLine();
        return money;
    }
}
