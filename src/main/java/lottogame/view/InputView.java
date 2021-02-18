package lottogame.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String input() {
        return scanner.nextLine().trim();
    }

    public static String inputMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        return input();
    }

    public static String inputWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return input();
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return input();
    }
}
