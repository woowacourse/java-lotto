package view;

import domain.Money;

import java.util.Scanner;

public class InputView {
    private static final String NUMBER_REGEX = "^[0-9]+$";

    private static Scanner scanner = new Scanner(System.in);

    public static Money enterMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        int money = parseMoney(scanner.nextLine());
        return new Money(money);
    }

    private static int parseMoney(String input) {
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new NumberFormatException("숫자를 입력해주세요.");
        }
    }
}
