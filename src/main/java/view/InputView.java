package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String PURCHASEMENT_MESSAGE = "구입할 금액을 입력해주세요.";
    private static final String INTEGER_FORMAT_EXCEPTION_MESSAGE = "정수만 입력할 수 있습니다.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해주세요.";
    private static final String NUMBER_OF_MANUAL_ISSUE_INPUT_MESSAGE = "수동으로 구매할 로또 수를 입력해주세요.";
    private static final String MANUAL_ISSUED_LOTTO_NUMBER_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputPurchaseAmount() {
        try {
            return inputNumber(PURCHASEMENT_MESSAGE);
        } catch (NumberFormatException e) {
            System.out.println(INTEGER_FORMAT_EXCEPTION_MESSAGE);
            return inputPurchaseAmount();
        }
    }

    private static int inputNumber(String inputMessage) {
        System.out.println(inputMessage);
        String number = SCANNER.nextLine().trim();
        return Integer.parseInt(number);
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println(WINNING_NUMBERS_INPUT_MESSAGE);
        return getNumbersForLotto();
    }

    public static List<Integer> getNumbersForLotto() {
        try {
            return Arrays.stream(SCANNER.nextLine().split(",\\s*"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println(INTEGER_FORMAT_EXCEPTION_MESSAGE);
            return getNumbersForLotto();
        }
    }

    public static int inputBonusNumber() {
        try {
            return inputNumber(BONUS_NUMBER_INPUT_MESSAGE);
        } catch (NumberFormatException e) {
            System.out.println(INTEGER_FORMAT_EXCEPTION_MESSAGE);
            return inputBonusNumber();
        }
    }

    public static int inputNumberOfManualIssue() {
        try {
            return inputNumber(NUMBER_OF_MANUAL_ISSUE_INPUT_MESSAGE);
        } catch (NumberFormatException e) {
            System.out.println(INTEGER_FORMAT_EXCEPTION_MESSAGE);
            return inputNumberOfManualIssue();
        }
    }

    public static void printInputMessageOfManualIssue() {
        System.out.println(MANUAL_ISSUED_LOTTO_NUMBER_INPUT_MESSAGE);
    }
}
