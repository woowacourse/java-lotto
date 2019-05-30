package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputMoney() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("잘못된 입력입니다.");
            return inputMoney();
        }
    }

    public static int inputNumberOfManualLottos() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static String inputLottosNumbers() {
        return SCANNER.nextLine();
    }

    public static int inputBonusBall() {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("잘못된 입력입니다.");
            return inputMoney();
        }
    }
}

