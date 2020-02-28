package lotto.parser;

public class InvalidNumberInputException extends IllegalArgumentException {

    private static final String NOT_NUMBER_EXCEPTION = "숫자만 입력 가능합니다.";

    InvalidNumberInputException() {
        super(NOT_NUMBER_EXCEPTION);
    }

}
