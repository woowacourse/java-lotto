package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String RECEIVE_PRICE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String RECEIVE_WINNING_NUMBERS_MESSAGE = "지난 당첨 번호를 입력해 주세요.";
    private static final String RECEIVE_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String NULL_ERROR = "null 이 입력되었습니다.";
    private static final String NOT_INT_ERROR = "정수가 아닙니다.";

    public static String receivePrice() {
        return receiveInput(RECEIVE_PRICE_MESSAGE);
    }

    public static List<Integer> receiveWinningNumbers() {
        return receiveNumbers(RECEIVE_WINNING_NUMBERS_MESSAGE);
    }

    public static int receiveBonusNumber() {
        return receiveNumber(RECEIVE_BONUS_NUMBER_MESSAGE);
    }

    private static int receiveNumber(final String message) {
        return createNumber(receiveInputNotNull(message));
    }

    private static List<Integer> receiveNumbers(final String message) {
        return createNumbers(receiveInputNotNull(message));
    }

    private static String receiveInputNotNull(final String message) {
        String userInput = receiveInput(message);
        validateNull(userInput);
        return userInput;
    }

    private static void validateNull(final String userInput) {
        if (userInput == null) {
            throw new IllegalArgumentException(NULL_ERROR);
        }
    }

    private static int createNumber(final String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INT_ERROR);
        }
    }

    private static List<Integer> createNumbers(final String userInput) {
        try {
            return Arrays.stream(userInput.split(","))
                .map(String::trim)
                .map(Integer::new)
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INT_ERROR);
        }
    }

    private static String receiveInput(final String message) {
        System.out.println(message);
        return SCANNER.nextLine().trim();
    }
}
