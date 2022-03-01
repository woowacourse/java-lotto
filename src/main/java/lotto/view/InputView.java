package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static void printGetManualLottosMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static String getManualLotto() {
        return scanner.nextLine();
    }

    public static String getWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
