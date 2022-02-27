package verus.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import verus.exception.ApplicationFinishedException;
import verus.exception.InvalidFormatException;

public class InputTemplate {

    private final Scanner scanner;
    private final PrintStream printStream;

    public InputTemplate(InputStream inputStream, OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = new PrintStream(outputStream);
    }

    public String repeatablyInput(String message, Consumer<String> validator,
        Consumer<? super InvalidFormatException> exceptionHandler) {
        try {
            String value = inputWithMessage(message);
            validator.accept(value);
            return value;
        } catch (InvalidFormatException e) {
            exceptionHandler.accept(e);
            return selectivelyRepeat(() -> repeatablyInput(message, validator, exceptionHandler));
        }
    }

    private String selectivelyRepeat(Supplier<String> supplier) {
        String value = chooseOptions("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        if (isRepeatable(value)) {
            return supplier.get();
        }
        throw new ApplicationFinishedException();
    }

    public String chooseOptions(String message, String... options) {
        String value = inputWithMessage(message);
        if (isChoosable(value, options)) {
            return value;
        }
        return chooseOptions(message, options);
    }

    private String inputWithMessage(String message) {
        printStream.println(message);
        return scanner.nextLine();
    }

    private boolean isChoosable(String value, String[] options) {
        return Stream.of(options).anyMatch(option -> option.equals(value));
    }

    private boolean isRepeatable(String value) {
        return value.equals("y") || value.equals("Y");
    }
}
