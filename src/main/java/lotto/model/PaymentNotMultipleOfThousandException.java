package lotto.model;

public class PaymentNotMultipleOfThousandException extends RuntimeException {
        public PaymentNotMultipleOfThousandException() {
                super();
        }

        public PaymentNotMultipleOfThousandException(String message) {
                super(message);
        }
}
