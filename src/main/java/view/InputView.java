package view;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class InputView {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static <T> T inputOnce(String message, Function<String, T> function) {
        System.out.println(message);
        return function.apply(SCANNER.nextLine());
    }

    public static <T> T inputSelectiveRepeatably(String message, Function<String, T> function, Consumer<Exception> errorHandler) {
        try {
            return inputOnce(message, function);
        } catch (Exception e) {
            errorHandler.accept(e);
            String value = inputSelectBox("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");

            if(value.equals("y") || value.equals("Y")) {
                return inputSelectiveRepeatably(message, function, errorHandler);
            }
        }

        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    public static String inputSelectBox(String message, String... options) {
        String value = inputOnce(message, Function.identity());

        if (Stream.of(options).anyMatch(option -> option.equals(value))) {
            return value;
        }

        return inputSelectBox(message, options);
    }

}
