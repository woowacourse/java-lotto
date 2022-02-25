package lotto.view;

import java.util.Scanner;

public class Input {
    private static final String NUMBER_MATCHES = "-?[0-9]+";
    private static final String ERROR_ONLY_NUMBER = "숫자를 입력해주세요!";
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputPayment(final Entering entering) {
        String payment = entering.enter();
        if (isBlank(payment) || !isNumber(payment)) {
            throw new IllegalArgumentException(ERROR_ONLY_NUMBER);
        }
        return Integer.parseInt(payment);
    }

    public static String inputWinNumber() {
        return input();
    }

    public static String inputBonusBall() {
        return input();
    }

    private static String input() {
        return scanner.nextLine();
    }

    private static boolean isBlank(final String text) {
        return text == null || text.isEmpty();
    }

    private static boolean isNumber(final String value) {
        return value.matches(NUMBER_MATCHES);
    }
}
