package lotto.view;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputTemplate {

    private static final Scanner scanner = new Scanner(System.in);

    private InputTemplate() {

    }

    public static String input(String message, StringFormatValidator validator,
        Consumer<Exception> exceptionHandler, Supplier<String> supplier) {
        return inputTemplate(() -> inputWithMessage(message), validator, exceptionHandler,
            supplier);
    }

    private static String inputWithMessage(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private static String inputTemplate(Supplier<String> input, StringFormatValidator validator,
        Consumer<Exception> exceptionHandler, Supplier<String> supplier) {
        try {
            String value = input.get();
            validator.validate(value);
            return value;
        } catch (IllegalArgumentException e) {
            exceptionHandler.accept(e);
            return supplier.get();
        }
    }

    public static String input(StringFormatValidator validator,
        Consumer<Exception> exceptionHandler, Supplier<String> supplier) {
        return inputTemplate(() -> inputWithoutMessage(), validator, exceptionHandler, supplier);
    }

    private static String inputWithoutMessage() {
        return scanner.nextLine();
    }

}
