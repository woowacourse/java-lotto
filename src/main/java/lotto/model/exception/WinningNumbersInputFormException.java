package lotto.model.exception;

public class WinningNumbersInputFormException extends RuntimeException {
        public WinningNumbersInputFormException() {
                super();
        }

        public WinningNumbersInputFormException(String message) {
                super(message);
        }
}
