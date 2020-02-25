package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputBuyMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.next();
    }

    public static String inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.next();
    }

    public static String inputWinNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.next();
    }

    public static String inputManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.next();
    }

    public static String inputManualLotto(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(scanner.next() + '\n');
        }
        return builder.toString();
    }
}
