package lotto.model.exception;

public class InvalidManualPaymentNumberException extends RuntimeException {
        public InvalidManualPaymentNumberException() {
                super();
        }

        public InvalidManualPaymentNumberException(String message) {
                super(message);
        }
}
