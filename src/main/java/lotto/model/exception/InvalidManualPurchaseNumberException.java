package lotto.model.exception;

public class InvalidManualPurchaseNumberException extends RuntimeException {
        public InvalidManualPurchaseNumberException() {
                super();
        }

        public InvalidManualPurchaseNumberException(String message) {
                super(message);
        }
}
