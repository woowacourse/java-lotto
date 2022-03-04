package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해주세요";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_CONTINUE_MESSAGE = "다시 시작 하려면 1, 종료하려면 0을 입력해주세요.";

    private static final Scanner input = new Scanner(System.in);

    public static String inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return input.nextLine().trim();
    }

    public static String inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return input.nextLine().trim();
    }

    public static String inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return input.nextLine().trim();
    }

    public static String inputManualLottoCount() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        return input.nextLine().trim();
    }

    public static void printManualLottoNumbersMessage() {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
    }

    public static String inputManualLottoNumbers() {
        return input.nextLine().trim();
    }

    public static String inputContinueNumber() {
        System.out.println();
        System.out.println(INPUT_CONTINUE_MESSAGE);
        return input.nextLine();
    }
}
