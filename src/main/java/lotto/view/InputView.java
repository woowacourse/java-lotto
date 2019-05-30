package lotto.view;

import lotto.Money;
import lotto.exception.InvalidPaymentException;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return new Money(SCANNER.nextLine());
        } catch (InvalidPaymentException e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }
}
