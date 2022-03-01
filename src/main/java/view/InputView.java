package view;

import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class InputView {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static <T> T inputWithMessage(String message, Function<String, T> function) {
        System.out.println(message);
        return function.apply(SCANNER.nextLine());
    }

    private static boolean isNotNumeric(String text) {
        try {
            Integer.parseInt(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isRepeatable() {
        String value = inputSelectBox("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        if (value.equals("y") || value.equals("Y")) {
            return true;
        }
        throw new IllegalStateException("시스템을 종료합니다.");
    }

    private static String inputSelectBox(String message, String... options) {
        String value = inputWithMessage(message, Function.identity());

        if (isIncludedInOptions(value, options)) {
            return value;
        }
        return inputSelectBox(message, options);
    }

    private static boolean isIncludedInOptions(String value, String[] options) {
        return Stream.of(options).anyMatch(option -> option.equals(value));
    }
}
