package lotto.view;

import static lotto.view.StringFormatValidator.NOT_WORKING_VALIDATOR;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lotto.exception.LottoException;

public class InputTemplate {

    private final static Scanner SCANNER = new Scanner(System.in);

    private InputTemplate() {

    }

    public static <T> T repeatablyExecute(Supplier<T> parser,
        Consumer<LottoException> exceptionHandler) {
        try {
            return parser.get();
        } catch (LottoException e) {
            return handleLottoException(parser, exceptionHandler, e);
        }
    }

    private static <T> T handleLottoException(Supplier<T> parser,
        Consumer<LottoException> exceptionHandler, LottoException e) {
        exceptionHandler.accept(e);
        if (isRepeatable()) {
            return repeatablyExecute(parser, exceptionHandler);
        }
        throw new LottoFinishedException();
    }

    private static boolean isRepeatable() {
        String value = chooseOptions("다시 시도하시려면 Y, 아니면 N", "Y", "N", "y", "n");
        return value.equals("y") || value.equals("Y");
    }

    private static String chooseOptions(String message, String... options) {
        String value = inputWithMessage(message, NOT_WORKING_VALIDATOR::validate);
        if (isChoosable(value, options)) {
            return value;
        }
        return chooseOptions(message, options);
    }

    private static String inputWithMessage(String message, Consumer<String> validator) {
        System.out.println(message);
        String value = SCANNER.nextLine();
        validator.accept(value);
        return value;
    }

    private static boolean isChoosable(String value, String[] options) {
        return Stream.of(options).anyMatch(option -> option.equals(value));
    }

    public static String repeatablyInput(String message, Consumer<String> validator,
        Consumer<InvalidFormatException> errorHandler) {
        try {
            return inputWithMessage(message, validator);
        } catch (InvalidFormatException e) {
            return handleInvalidFormatException(message, validator, errorHandler, e);
        }
    }

    private static String handleInvalidFormatException(String message, Consumer<String> validator,
        Consumer<InvalidFormatException> errorHandler, InvalidFormatException e) {
        errorHandler.accept(e);
        if (isRepeatable()) {
            return repeatablyInput(message, validator, errorHandler);
        }
        throw new LottoFinishedException();
    }

}
