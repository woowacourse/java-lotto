package lotto.view;

import java.util.Arrays;
import java.util.Scanner;

public class InputView {
    public static final String NON_NUMERIC_ERROR = "숫자만 입력 가능합니다.";

    private InputView() {
    }

    public static int takeMoneyInput(Scanner scanner) {
        System.out.println("구입금액을 입력해 주세요");
        String moneyInput = scanner.nextLine();
        validateNumeric(moneyInput);
        return Integer.parseInt(moneyInput);
    }

    public static int[] takeWinningNumbersInput(Scanner scanner) {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] winningNumberInput = scanner.nextLine().split(", ");
        for (String number : winningNumberInput) {
            validateNumeric(number);
        }
        return convertStringsToInts(winningNumberInput);
    }

    public static int[] convertStringsToInts(String[] winningNumberInput) {
        return Arrays.stream(winningNumberInput)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int takeBonusNumberInput(Scanner scanner) {
        System.out.println("보너스 볼을 입력해 주세요");
        String bonusBallInput = scanner.nextLine();
        validateNumeric(bonusBallInput);
        System.out.println();
        return Integer.parseInt(bonusBallInput);
    }

    private static void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NON_NUMERIC_ERROR);
        }
    }
}
