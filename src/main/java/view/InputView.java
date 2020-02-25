package view;

import java.util.*;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static String enterMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        return scanner.nextLine();
    }

    public static String enterLastWeekWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String enterBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
