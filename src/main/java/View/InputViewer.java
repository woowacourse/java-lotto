package View;

import java.util.Scanner;

public class InputViewer {
    private static Scanner scanner = new Scanner(System.in);

    private InputViewer() {
    }

    public static String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String inputWinningLottoTicketNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String inputBonusBallNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
