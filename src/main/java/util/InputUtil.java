package util;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";

    private InputUtil() {
    }

    public static int nextInt() {
        String inputValue = SCANNER.nextLine();
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(String.format("자연수만 입력 가능합니다. | 현재 입력 값 : %s ", inputValue));
        }
    }

    public static Set<Integer> inputWinningNumbers() {
        String winningNumbersText = SCANNER.nextLine();
        return Arrays.stream(winningNumbersText.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public static int inputBonusNumber() {
        return nextInt();
    }
}
