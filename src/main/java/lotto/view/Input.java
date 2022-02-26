package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Input {
    private static final String NUMBER_MATCHES = "-?[0-9]+";
    private static final String ERROR_ONLY_NUMBER = "숫자를 입력해주세요!";
    private static final String DELIMITER = ",";

    public static int inputPayment(final Entering entering) {
        String payment = entering.enter();
        validateNumber(payment);
        return Integer.parseInt(payment);
    }

    public static List<Integer> inputWinNumber(final Entering entering) {
        String[] winNumber = getWinNumber(entering);
        for (String number : winNumber) {
            validateNumber(number);
        }
        return Arrays.stream(winNumber)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonusBall(final Entering entering) {
        String bonusBall = entering.enter();
        validateNumber(bonusBall);
        return Integer.parseInt(bonusBall);
    }

    private static String[] getWinNumber(final Entering entering) {
        String winNumber = entering.enter();
        if (isBlank(winNumber)) {
            throw new IllegalArgumentException(ERROR_ONLY_NUMBER);
        }
        return winNumber.split(DELIMITER);
    }

    private static void validateNumber(String input) {
        if (isBlank(input) || !isNumber(input)) {
            throw new IllegalArgumentException(ERROR_ONLY_NUMBER);
        }
    }

    private static boolean isBlank(final String text) {
        return text == null || text.isEmpty();
    }

    private static boolean isNumber(final String value) {
        return value.matches(NUMBER_MATCHES);
    }
}
