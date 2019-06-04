package lotto.view.inputview;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner;

    public static String inputPrice() {
        scanner = new Scanner(System.in);
        System.out.println("구매 금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String inputWinningNum() {
        scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static Integer inputBonusBall() {
        scanner = new Scanner(System.in);
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
