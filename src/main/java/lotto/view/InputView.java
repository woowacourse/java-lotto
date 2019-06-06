package lotto.view;

import java.util.Scanner;

public class InputView {
    private static Scanner SCANNER = new Scanner(System.in);

    public static String inputPayment() {
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String inputCountOfManualLotto() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String inputLottoNumber() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String inputWinningLottoNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }
}
