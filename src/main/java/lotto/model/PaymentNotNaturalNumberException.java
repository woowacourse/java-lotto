package lotto.model;

public class PaymentNotNaturalNumberException extends RuntimeException {
        public PaymentNotNaturalNumberException() {
                super();
        }

        public PaymentNotNaturalNumberException(String message) {
                super(message);
        }
}
