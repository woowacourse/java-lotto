package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String ERROR_NON_INTEGER_FORMAT = "구입 금액은 숫자 형태로 입력해야 합니다.";
    private static final String ERROR_NULL_OR_EMPTY = "입력 null 혹은 빈 문자열 일수 없습니다.";

    public static List<Integer> splitNumbers(String input) {
        String[] inputNumbers = input.split(", ");
        return Arrays.stream(inputNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int toInteger(String inputMoney) {
        try {
            return Integer.parseInt(inputMoney);
        } catch (NumberFormatException numberFormatException) {
            throw new RuntimeException(ERROR_NON_INTEGER_FORMAT);
        }
    }

    public static void validateIsNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new RuntimeException(ERROR_NULL_OR_EMPTY);
        }
    }
}
