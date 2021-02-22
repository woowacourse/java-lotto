package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구매금액을 입력해 주세요.";
    private static final String LOTTO_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static String getInputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String getLottoNumbers() {
        System.out.println(LOTTO_NUMBERS_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String getBonusBallNumber() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        String value = SCANNER.nextLine();
        System.out.println();
        return value;
    }
}