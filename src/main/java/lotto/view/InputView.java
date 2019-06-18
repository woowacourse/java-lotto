package lotto.view;

import java.util.Scanner;

public class InputView {
    public static final String PAYMENT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String COUNT_OF_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    public static final String WINNING_LOTTO_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static Scanner SCANNER = new Scanner(System.in);

    public static String inputPayment() {
        System.out.println(PAYMENT_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String inputCountOfManualLotto() {
        System.out.println(COUNT_OF_MANUAL_LOTTO_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String inputLottoNumber() {
        System.out.println(LOTTO_NUMBER_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String inputWinningLottoNumber() {
        System.out.println(WINNING_LOTTO_NUMBER_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String inputBonusBall() {
        System.out.println(BONUS_BALL_MESSAGE);
        return SCANNER.nextLine();
    }
}
