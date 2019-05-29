package lotto.view;

import lotto.domain.Money;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money createMoney() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            return new Money(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return createMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createMoney();
        }
    }
}
