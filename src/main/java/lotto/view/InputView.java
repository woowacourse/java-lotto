package lotto.view;

import java.util.Scanner;

public class InputView {
    public static String takeMoneyInput(Scanner scanner) {
        String money = scanner.nextLine();
        validateNumber(money);
        validateMoneyLowerThan1000(money);
        return money;
    }

    private static void validateNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 정수만 입력 가능합니다");
        }
    }

    private static void validateMoneyLowerThan1000(String money) {
        if (Integer.parseInt(money) < 1000) {
            throw new IllegalArgumentException("1000원 이상의 금액만 입력 가능합니다.");
        }
    }
}
