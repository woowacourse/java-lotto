package lotto.view;

import static lotto.view.StringFormatValidator.NOT_WORKING_VALIDATOR;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lotto.model.exception.LottoException;
import lotto.view.exception.InvalidFormatException;
import lotto.view.exception.LottoFinishedException;

public class InputTemplate {

    private final Scanner scanner;
    private final PrintStream printStream;

    public InputTemplate(InputStream inputStream, OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = new PrintStream(outputStream);
    }

    public <T> T repeatablyExecute(Supplier<T> supplier,
        Consumer<LottoException> exceptionHandler) {
        try {
            return supplier.get();
        } catch (LottoException e) {
            exceptionHandler.accept(e);
            return selectivelyRepeat(() -> repeatablyExecute(supplier, exceptionHandler));
        }
    }

    private <T> T selectivelyRepeat(Supplier<T> supplier) {
        String value = chooseOptions("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        if (isRepeatable(value)) {
            return supplier.get();
        }
        throw new LottoFinishedException();
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
