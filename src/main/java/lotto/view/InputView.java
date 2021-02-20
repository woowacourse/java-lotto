package lotto.view;

import java.util.Scanner;

public class InputView {
    private InputView() {

    }

    public static String takeMoneyInput(Scanner scanner) {
        System.out.println("구입금액을 입력해 주세요");
        return scanner.nextLine();
    }

    public static String[] takeWinningNumbersInput(Scanner scanner) {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumberInput = scanner.nextLine();
        return winningNumberInput.split(", ");
    }

    public static String takeBonusNumberInput(Scanner scanner) {
        System.out.println("보너스 볼을 입력해 주세요");
        return scanner.nextLine();
    }
}
