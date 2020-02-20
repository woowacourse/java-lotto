package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static int inputMoney() {
        OutputView.printMoneyFormat();
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닌 값이 입력되었습니다.");
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
            throw new IllegalArgumentException("잘못된 번호가 입력되었습니다.");
        }
    }

    public static int inputBonusNumber() {
        OutputView.printBonusNumberFormat();
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닌 값이 입력되었습니다.");
        }
    }
}
