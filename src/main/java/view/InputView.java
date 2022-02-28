package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String NOT_DIGIT_MONEY_EXCEPTION_MESSAGE = "금액은 숫자만 입력해야 합니다";
    private static final String NOT_DIGIT_WINNING_NUMBER_EXCEPTION_MESSAGE = "당첨 번호는 숫자만 입력해야 합니다";
    private static final String NOT_DIGIT_BONUS_NUMBER_EXCEPTION_MESSAGE = "보너스 번호는 숫자만 입력해야 합니다";
    private static final Scanner SCANNER = new Scanner(System.in);

    public List<Integer> requestWinningNumbers() {
        try {
            return Arrays.stream(splitByDelimiter(readLine()))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NOT_DIGIT_WINNING_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private String[] splitByDelimiter(String userInput) {
        return userInput.split(DELIMITER);
    }

    public int requestMoney() {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NOT_DIGIT_MONEY_EXCEPTION_MESSAGE);
        }
    }

    public int requestBonusNumber() {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NOT_DIGIT_BONUS_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private String readLine() {
        return SCANNER.nextLine();
    }
}
