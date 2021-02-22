package lottogame.view;

import java.util.Scanner;

public class InputView {

    public static final String INPUT_REQUEST_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String INPUT_REQUEST_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_REQUEST_MONEY = "구입금액을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    public static String getMoneyInput() {
        System.out.println(INPUT_REQUEST_MONEY);
        return scanner.nextLine();
    }

    public static String getWinningNumbersInput() {
        System.out.println(INPUT_REQUEST_WINNING_NUMBERS);
        return scanner.nextLine();
    }

    public static String getBonusNumberInput() {
        System.out.println(INPUT_REQUEST_BONUS_NUMBER);
        return scanner.nextLine();
    }
}
