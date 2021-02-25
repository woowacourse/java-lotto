package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String MESSAGE_BUDGET = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String MESSAGE_MANUAL_QUANTITY = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MESSAGE_MANUAL_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요.";
    private static final String BLANK = "";
    private static final String COMMA = ",";
    private static final String NUMBER_REGULAR_EXPRESSION = "^-?[0-9]+$";
    private static final String ERROR_INVALID_BLANK = "[ERROR] 값을 입력해주세요.";
    private static final String ERROR_INVALID_DELIMITER = "[ERROR] 구분자는 ,로 입력해주세요.";
    private static final String ERROR_INVALID_NUMBER_FORMAT = "[ERROR] 문자가 아닌 숫자만 입력해주세요.";

    private static InputView instance;

    private final Scanner scanner = new Scanner(System.in);

    public static InputView getInstance() {
        if (instance == null) {
            instance = new InputView();
        }
        return instance;
    }

    public long scanBudget() {
        System.out.println(MESSAGE_BUDGET);
        return Long.parseLong(scanInputString());
    }

    public long scanManualQuantity() {
        System.out.println(MESSAGE_MANUAL_QUANTITY);
        return Long.parseLong(scanInputString());
    }

    public List<Integer> scanWinningNumbers() {
        System.out.println(MESSAGE_WINNING_NUMBER);
        return scanNumbers();
    }

    public List<List<Integer>> scanManualNumbers(long manualCount) {
        System.out.println(MESSAGE_MANUAL_NUMBERS);
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualNumbers.add(scanNumbers());
        }
        return manualNumbers;
    }

    public int scanBonusBall() {
        System.out.println(MESSAGE_BONUS_BALL);
        return Integer.parseInt(scanInputString());
    }

    private String scanInputString() {
        String inputString = deleteWhiteSpaces(scanner.nextLine());
        if (inputString.equals(BLANK)) {
            throw new IllegalArgumentException(ERROR_INVALID_BLANK);
        }
        if (inputString.matches(NUMBER_REGULAR_EXPRESSION)) {
            return inputString;
        }
        throw new IllegalArgumentException(ERROR_INVALID_NUMBER_FORMAT);
    }

    private List<Integer> scanNumbers() {
        String inputString = deleteWhiteSpaces(scanner.nextLine());
        if (inputString.contains(COMMA)) {
            return Arrays.stream(inputString.split(COMMA))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException(ERROR_INVALID_DELIMITER);
    }

    private String deleteWhiteSpaces(String string) {
        return string.replaceAll("\\s+", "");
    }
}
