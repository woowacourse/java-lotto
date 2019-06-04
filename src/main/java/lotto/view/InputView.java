package lotto.view;

import lotto.domain.*;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            int money = Integer.parseInt(SCANNER.nextLine());
            return new Money(money);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return getMoney();
        }
    }
}
