package lotto.exception;

public class DuplicatedInputException extends IllegalArgumentException {
    public DuplicatedInputException() {
        super();
    }

    public DuplicatedInputException(String s) {
        super(s);
    }
}
