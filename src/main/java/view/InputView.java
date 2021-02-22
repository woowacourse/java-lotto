package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String MESSAGE_BUDGET = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_BONUS_BALL = "보너스 볼을 입력해 주세요.";
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
        String inputString = scanStringBudget();
        if (inputString.equals(BLANK)) {
            System.out.println(ERROR_INVALID_BLANK);
            return scanBudget();
        }
        if (inputString.matches(NUMBER_REGULAR_EXPRESSION)) {
            return Long.parseLong(inputString);
        }
        System.out.println(ERROR_INVALID_NUMBER_FORMAT);
        return scanBudget();
    }

    private String scanStringBudget() {
        System.out.println(MESSAGE_BUDGET);
        return deleteWhiteSpaces(scanner.nextLine());
    }

    public List<Integer> scanWinningNumbers() {
        String inputString = scanStringWinningNumbers();
        if (inputString.contains(COMMA)) {
            return Arrays.stream(inputString.split(COMMA))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        System.out.println(ERROR_INVALID_DELIMITER);
        return scanWinningNumbers();
    }

    private String scanStringWinningNumbers() {
        System.out.println(MESSAGE_WINNING_NUMBER);
        return deleteWhiteSpaces(scanner.nextLine());
    }

    public int scanBonusBall() {
        String inputString = scanStringBonusBall();
        if (inputString.equals(BLANK)) {
            System.out.println(ERROR_INVALID_BLANK);
            return scanBonusBall();
        }
        if (inputString.matches(NUMBER_REGULAR_EXPRESSION)) {
            return Integer.parseInt(inputString);
        }
        System.out.println(ERROR_INVALID_NUMBER_FORMAT);
        return scanBonusBall();
    }

    private String scanStringBonusBall() {
        System.out.println(MESSAGE_BONUS_BALL);
        return deleteWhiteSpaces(scanner.nextLine());
    }

    private String deleteWhiteSpaces(String string) {
        return string.replaceAll("\\s+", "");
    }
}
