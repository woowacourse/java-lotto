package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lotto.validator.NumberValidator;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static int inputPurchaseAmount() {
        String value = scanner.nextLine();
        NumberValidator.validateNumber(value);
        return Integer.parseInt(value);
    }

    public static int inputManualLottoCount() {
        String value = scanner.nextLine();
        NumberValidator.validateNumber(value);
        return Integer.parseInt(value);
    }

    public static List<Integer> inputLottoNumbers() {
        List<String> numbers = Arrays.asList(scanner.nextLine().split(DELIMITER)).stream()
            .map(String::trim)
            .collect(Collectors.toList());

        validateNumbers(numbers);

        return numbers.stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static void validateNumbers(List<String> numbers) {
        for (String number : numbers) {
            NumberValidator.validateNumber(number);
        }
    }

    public static int inputBonusBall() {
        String value = scanner.nextLine();
        NumberValidator.validateNumber(value);
        return Integer.parseInt(value);
    }
}
