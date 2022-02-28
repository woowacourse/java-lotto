package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String ASK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String ASK_MONEY_MESSAGE = "구입금액을 입력해주세요.";

    private final static Scanner scanner = new Scanner(System.in);

    public static String askMoneyInput() {
        System.out.println(ASK_MONEY_MESSAGE);
        return scanner.nextLine().trim();
    }

    public static String askWinningNumbers() {
        System.out.println(ASK_WINNING_NUMBER_MESSAGE);
        return scanner.nextLine();
    }

    public static String askBonusNumber() {
        System.out.println(ASK_BONUS_BALL_MESSAGE);
        return scanner.nextLine().trim();
    }

}
