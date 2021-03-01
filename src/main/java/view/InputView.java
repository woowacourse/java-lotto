package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String RECEIVE_PRICE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String RECEIVE_MANUAL_INPUT_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String RECEIVE_MANUAL_INPUT_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String RECEIVE_WINNING_NUMBERS_MESSAGE = "지난 당첨 번호를 입력해 주세요.";
    private static final String RECEIVE_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String ERROR_NULL = "null 이 입력되었습니다.";
    private static final String ERROR_NOT_INTEGER = "정수가 아닙니다.";

    public static int payment() {
        return parseToInteger(receiveInput(RECEIVE_PRICE_MESSAGE));
    }

    public static int manualCount() {
        return parseToInteger(receiveInput(RECEIVE_MANUAL_INPUT_COUNT));
    }

    public static List<List<Integer>> manualNumbers(final int manualCount) {
        if (manualCount == 0) {
            return Collections.emptyList();
        }

        System.out.println(RECEIVE_MANUAL_INPUT_NUMBERS);
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualCount; ++i) {
            manualNumbers.add(createNumbers(SCANNER.nextLine().trim()));
        }
        return manualNumbers;
    }

    public static List<Integer> winningNumbers() {
        return receiveNumbers(RECEIVE_WINNING_NUMBERS_MESSAGE);
    }

    public static int bonusNumber() {
        return receiveNumber(RECEIVE_BONUS_NUMBER_MESSAGE);
    }

    private static int receiveNumber(final String message) {
        return parseToInteger(receiveInputNotNull(message));
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
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }

    private static int parseToInteger(final String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    private static List<Integer> createNumbers(final String userInput) {
        try {
            return Arrays.stream(userInput.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    private static String receiveInput(final String message) {
        System.out.println(message);
        return SCANNER.nextLine().trim();
    }
}
