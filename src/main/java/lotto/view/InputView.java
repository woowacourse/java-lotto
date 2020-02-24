package lotto.view;

import lotto.exception.*;
import lotto.utils.LottoRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String EMPTY_STRING = "";
    private static final String NULL_OR_EMPTY_EXCEPTION_MESSAGE = "null 혹은 빈문자열을 입력할 수 없습니다.";
    private static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하세요.";

    private static Scanner scanner = new Scanner(System.in);

    public static String input() {
        String input = scanner.nextLine();
        checkNullorEmptyInput(input);
        return input;
    }

    public static void checkNullorEmptyInput(String input) {
        if (input == null || input.equals(EMPTY_STRING)) {
            throw new NullOrEmptyException(NULL_OR_EMPTY_EXCEPTION_MESSAGE);
        }
    }

    public static int inputPayment() {
        String input = scanner.nextLine();
        checkNumberFormat(input);
        int payment = Integer.parseInt(input);
        return payment;
    }

    public static int inputBonusBall() {
        String input = scanner.nextLine();
        checkNumberFormat(input);
        return Integer.parseInt(input);
    }

    public static List<Integer> inputWinNumber() {
        String inputs = scanner.nextLine();
        List<Integer> winningNumbers = makeWinNumbers(makeNumbers(inputs));
        return winningNumbers;
    }

    public static List<String> makeNumbers(String winNumber) {
        return Arrays.asList(winNumber.split(","));
    }

    public static List<Integer> makeWinNumbers(List<String> inputs) {
        List<Integer> numbers = new ArrayList<>();
        for (String input : inputs) {
            input = input.trim();
            checkNumberFormat(input);
            int number = Integer.parseInt(input);
            numbers.add(number);
        }
        return numbers;
    }

    public static void checkNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
