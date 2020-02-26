package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String ERROR_NUMBER_FORMAT_MESSAGE = "정수가 아닌 값이 입력되었습니다.";

    private InputView() {
        throw new AssertionError();
    }

    public static int inputMoney() {
        OutputView.printMoneyFormat();
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER_FORMAT_MESSAGE);
        }
    }

    public static int inputUserRepeat() {
        OutputView.printUserLottoGameRepeatFormat();
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER_FORMAT_MESSAGE);
        }
    }

    public static List<Integer> inputUserLottoNumbers() {
        try {
            return splitAndParse(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER_FORMAT_MESSAGE);
        }
    }

    private static List<Integer> splitAndParse(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> inputWinnerNumbers() {
        OutputView.printWinnerNumbersFormat();
        try {
            return splitAndParse(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER_FORMAT_MESSAGE);
        }
    }

    public static int inputBonusNumber() {
        OutputView.printBonusNumberFormat();
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER_FORMAT_MESSAGE);
        }
    }
}
