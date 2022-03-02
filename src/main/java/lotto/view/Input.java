package lotto.view;

import lotto.domain.Ball;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Input {
    private static final String NUMBER_MATCHES = "-?[0-9]+";
    private static final String ERROR_ONLY_NUMBER = "숫자를 입력해주세요!";
    private static final String DELIMITER = ",";

    private Input() {}

    public static int inputPayment(final Entering entering) {
        String payment = entering.enter();
        validateNumber(payment);
        return Integer.parseInt(payment);
    }

    public static List<Ball> inputWinningNumbers(final Entering entering) {
        String[] winningNumbers = getWinningNumbers(entering);
        for (String number : winningNumbers) {
            validateNumber(number);
        }
        return Arrays.stream(winningNumbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .map(Ball::new)
                .collect(Collectors.toList());
    }

    public static int inputBonusBall(final Entering entering) {
        String bonusBall = entering.enter();
        validateNumber(bonusBall);
        return Integer.parseInt(bonusBall);
    }

    private static String[] getWinningNumbers(final Entering entering) {
        String winningNumbers = entering.enter();
        if (isBlank(winningNumbers)) {
            throw new IllegalArgumentException(ERROR_ONLY_NUMBER);
        }
        return winningNumbers.split(DELIMITER);
    }

    private static void validateNumber(final String input) {
        if (isBlank(input) || !isNumber(input.trim())) {
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
