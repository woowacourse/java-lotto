package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private static final String DELIMITER = ",";
    private static final int INCLUDE_EMPTY = -1;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static <T> T inputWithMessage(String message, Function<String, T> function) {
        System.out.println(message);
        return function.apply(SCANNER.nextLine());
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

    public static List<String> splitAndTrim(String text) {
        return trimAll(splitWithEmpty(text));
    }

    private static List<String> trimAll(List<String> tokens) {
        return tokens.stream()
                .map(token -> token.trim())
                .collect(Collectors.toList());
    }

    private static List<String> splitWithEmpty(String text) {
        return Arrays.asList(text.split(DELIMITER, INCLUDE_EMPTY));
    }
}
