package lotto.view;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    public static <T> T repeatablyExecute(Supplier<T> supplier, Consumer<Exception> errorHandler) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return handleException(supplier, errorHandler, e);
        }
    }

    private static <T> T handleException(Supplier<T> supplier, Consumer<Exception> errorHandler, Exception e) {
        errorHandler.accept(e);
        if (isRepeatable()) {
            return repeatablyExecute(supplier, errorHandler);
        }
        throw new IllegalStateException("종료되었습니다!");
    }

    public static <T> T repeatablyInput(String message, Function<String, T> function,
        Consumer<Exception> errorHandler) {
        try {
            return inputWithMessage(message, function);
        } catch (Exception e) {
            return handleException(message, function, errorHandler, e);
        }
    }

    private static <T> T inputWithMessage(String message, Function<String, T> function) {
        System.out.println(message);
        return function.apply(SCANNER.nextLine());
    }

    private static <T> T handleException(String message, Function<String, T> function,
        Consumer<Exception> errorHandler,
        Exception e) {
        errorHandler.accept(e);
        if (isRepeatable()) {
            return repeatablyInput(message, function, errorHandler);
        }
        throw new IllegalStateException("종료되었습니다!");
    }

    private static boolean isRepeatable() {
        String value = chooseOptions("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        return value.equals("y") || value.equals("Y");
    }

    private static String chooseOptions(String message, String... options) {
        String value = inputWithMessage(message, Function.identity());
        if (isChoosable(value, options)) {
            return value;
        }
        return chooseOptions(message, options);
    }

    private static boolean isChoosable(String value, String[] options) {
        return Stream.of(options).anyMatch(option -> option.equals(value));
    }

}
