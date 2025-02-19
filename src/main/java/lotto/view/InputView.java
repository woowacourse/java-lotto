package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private InputView() {
    }

    public static int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return parseInt(input());
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return parseWinningNumbers(input());
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return parseInt(input());
    }

    private static List<Integer> parseWinningNumbers(final String input) {
        String[] inputWinningNumbers = input.split(",");
        List<Integer> winningNumbers = new ArrayList<>();
        for (final String inputWinningNumber : inputWinningNumbers) {
            winningNumbers.add(parseInt(inputWinningNumber.trim()));
        }
        return winningNumbers;
    }

    private static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요. [입력: %s]".formatted(input));
        }
    }

    private static String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
