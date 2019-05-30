package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String NEW_LINE = System.getProperty("line.separator");

    private static final Scanner sc = new Scanner(System.in);

    public static int inputBudget() {
        try {
            System.out.println(NEW_LINE + "구입 금액을 입력해주세요.");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요.");
            return inputBudget();
        }
    }

    public static int inputCountOfManualLotto() {
        try {
            System.out.println(NEW_LINE + "수동으로 구매할 로또 수를 입력해 주세요.");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요.");
            return inputCountOfManualLotto();
        }
    }

    public static String inputManualLotto() {
        return sc.nextLine();
    }

    public static String inputWinningLotto() {
        System.out.println(NEW_LINE + "당첨 번호를 입력해주세요.");
        return sc.nextLine();
    }

    public static int inputBonusNo() {
        try {
            System.out.println(NEW_LINE + "보너스 번호를 입력해주세요.");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요.");
            return inputBonusNo();
        }
    }
}
