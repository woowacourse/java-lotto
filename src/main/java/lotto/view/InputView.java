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

    public static int getNumberOfManualLotto(Money money) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            int numberOfManualLotto = Integer.parseInt(SCANNER.nextLine());
            validateNumberOfManualLotto(numberOfManualLotto, money);
            return numberOfManualLotto;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return getNumberOfManualLotto(money);
        }
    }

    private static void validateNumberOfManualLotto(int numberOfManualLotto, Money money) {
        if (numberOfManualLotto < 0) {
            throw new IllegalArgumentException("0 이상의 숫자만 입력할 수 있습니다.");
        }
        if (numberOfManualLotto > money.getNumberOfLotto()) {
            throw new IllegalArgumentException("구매 금액을 초과하는 숫자입니다.");
        }
    }
}
