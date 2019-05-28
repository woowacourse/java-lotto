package lotto.view;

import lotto.view.validator.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static final String WINNING_NUMBER_DELIMITER = ", ";
    private static Scanner scanner = new Scanner(System.in);

    public static int inputBuyPrice() {
        String input = "";
        do {
            input = scanner.nextLine();
        } while (!InputValidator.inputValidateBuyPrice(input));
        return Integer.parseInt(input);
    }

    public static List<Integer> inputWinningNumber() {
        String input = "";
        do {
            input = scanner.nextLine();
        } while (!InputValidator.inputValidateWinningNumber(input));
        return Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
