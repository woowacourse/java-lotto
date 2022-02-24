package view;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class InputView {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static <T> T inputSelectiveRepeatably(String message, Function<String, T> function,
                                                 Consumer<Exception> errorHandler) {
        try {
            return inputOnce(message, function);
        } catch (Exception e) {
            return handleException(message, function, errorHandler, e);
        }
    }

    private static <T> T inputOnce(String message, Function<String, T> function) {
        System.out.println(message);
        return function.apply(SCANNER.nextLine());
    }

    private static <T> T handleException(String message, Function<String, T> function, Consumer<Exception> errorHandler,
                              Exception e) {
        errorHandler.accept(e);
        if (isRepeatable()) {
            return inputSelectiveRepeatably(message, function, errorHandler);
        }
        throw new IllegalStateException("종료되었습니다!");
    }

    private static boolean isRepeatable() {
        String value = inputSelectBox("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        return value.equals("y") || value.equals("Y");
    }

    private static String inputSelectBox(String message, String... options) {
        String value = inputOnce(message, Function.identity());

        if (isIncludedInOptions(value, options)) {
            return value;
        }
        return inputSelectBox(message, options);
    }

    private static boolean isIncludedInOptions(String value, String[] options) {
        return Stream.of(options).anyMatch(option -> option.equals(value));
    }

}
