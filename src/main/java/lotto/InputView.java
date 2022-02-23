package lotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = SCANNER.nextLine();
        validateBlank(input);
        return validateNumber(input);
    }

    public static List<Number> inputNumbers() {
        String input = SCANNER.nextLine();
        return Collections.emptyList();
    }

    public static Number inputNumber() {
        return new Number(SCANNER.nextInt());
    }

    private static void validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 비어있을 수 없다.");
        }
    }

    private static int validateNumber(String input) {
        if (isNumeric(input)) {
            return Integer.parseInt(input);
        }
        throw new IllegalArgumentException("구입금액은 숫자이어야 한다.");
    }

    private static boolean isNumeric(String value) {
        return value.chars().allMatch(Character::isDigit);
    }
}
