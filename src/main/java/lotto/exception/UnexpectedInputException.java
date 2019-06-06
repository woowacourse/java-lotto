package lotto.exception;

public class UnexpectedInputException extends IllegalArgumentException {
    public UnexpectedInputException() {
        super();
    }

    public UnexpectedInputException(String s) {
        super(s);
    }
}
