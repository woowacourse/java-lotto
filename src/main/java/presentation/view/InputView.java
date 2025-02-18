package presentation.view;

import java.util.Scanner;

public class InputView {
    public static String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return getInput();
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getInput();
    }

    public static String inputAnswerNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return getInput();
    }

    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().trim();
    }
}
