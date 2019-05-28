package lotto.model.exception;

public class WinnerNumbersInputFormException extends RuntimeException {
        public WinnerNumbersInputFormException() {
                super();
        }

        public WinnerNumbersInputFormException(String message) {
                super(message);
        }
}
