package view;

import domain.Money;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money inputPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int money = SCANNER.nextInt();
        return new Money(money);
    }
}
