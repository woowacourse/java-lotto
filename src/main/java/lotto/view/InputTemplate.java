package lotto.view;

import static lotto.view.StringFormatValidator.NOT_WORKING_VALIDATOR;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lotto.view.exception.InvalidFormatException;
import lotto.view.exception.ApplicationFinishedException;

public class InputTemplate {

    private final Scanner scanner;
    private final PrintStream printStream;

    public InputTemplate(InputStream inputStream, OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = new PrintStream(outputStream);
    }

    public <T> T repeatablyExecute(Supplier<T> supplier, Consumer<? super Exception> handler,
        Class<? extends Exception> cls) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return handleException(() -> repeatablyExecute(supplier, handler, cls),
                handler, cls, e);
        }
    }

    private <T> T handleException(Supplier<T> supplier, Consumer<? super Exception> handler,
        Class<? extends Exception> cls, Exception thrown) {
        if (cls.isAssignableFrom(thrown.getClass())) {
            handler.accept(thrown);
            return selectivelyRepeat(supplier);
        }
        throw new ApplicationFinishedException(thrown);
    }

    private <T> T selectivelyRepeat(Supplier<T> supplier) {
        String value = chooseOptions("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        if (isRepeatable(value)) {
            return supplier.get();
        }
        throw new ApplicationFinishedException();
    }

    private String chooseOptions(String message, String... options) {
        String value = inputWithMessage(message, NOT_WORKING_VALIDATOR::validate);
        if (isChoosable(value, options)) {
            return value;
        }
        return chooseOptions(message, options);
    }

    private String inputWithMessage(String message, Consumer<String> validator) {
        printStream.println(message);
        return scanner.nextLine();
    }

    private boolean isChoosable(String value, String[] options) {
        return Stream.of(options).anyMatch(option -> option.equals(value));
    }

    private boolean isRepeatable(String value) {
        return value.equals("y") || value.equals("Y");
    }

    public String repeatablyInput(String message, Consumer<String> validator,
        Consumer<InvalidFormatException> exceptionHandler) {
        try {
            String value = inputWithMessage(message, validator);
            validator.accept(value);
            return value;
        } catch (InvalidFormatException e) {
            exceptionHandler.accept(e);
            return selectivelyRepeat(() -> repeatablyInput(message, validator, exceptionHandler));
        }
    }

}
