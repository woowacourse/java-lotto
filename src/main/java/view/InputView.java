package view;

import java.util.Scanner;

public class InputView {
    private static final String PURCHASEMENT_MESSAGE = "구입할 금액을 입력해주세요.";
    private static final String INTEGER_FORMAT_EXCEPTION_MESSAGE = "정수만 입력할 수 있습니다.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해주세요.";
    private static final String NUMBER_OF_MANUAL_ISSUE_INPUT_MESSAGE = "수동으로 구매할 로또 수를 입력해주세요.";
    private static final String MANUAL_ISSUED_LOTTO_NUMBER_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputPurchaseAmount() {
        System.out.println(PURCHASEMENT_MESSAGE);
        return getTrimmedStringInput();
    }

    private static String getTrimmedStringInput() {
        return SCANNER.nextLine().trim();
    }

    public static void printInputMessageOfManualIssue() {
        System.out.println(MANUAL_ISSUED_LOTTO_NUMBER_INPUT_MESSAGE);
    }

    public static String inputNumberOfManualIssue() {
        System.out.println(NUMBER_OF_MANUAL_ISSUE_INPUT_MESSAGE);
        return getTrimmedStringInput();
    }

    public static String getNumbersForLotto() {
        return SCANNER.nextLine();
    }

    public static String inputWinningNumbers() {
        System.out.println(WINNING_NUMBERS_INPUT_MESSAGE);
        return getNumbersForLotto();
    }

    public static String inputBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
        return getTrimmedStringInput();
    }
}
