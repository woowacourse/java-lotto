package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int requestAmount() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
            return requestAmount();
        }
    }

    public static String requestWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int requestBonusNumber() {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
            return requestAmount();
        }
    }
}
