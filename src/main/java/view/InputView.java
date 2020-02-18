package view;

import domain.Money;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static Money enterMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        int money = parseMoney(scanner.nextLine());
        return new Money(money);
    }

    private static int parseMoney(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자를 입력해주세요.");
        }
    }
}
