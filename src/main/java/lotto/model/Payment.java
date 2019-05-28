package lotto.model;

import lotto.model.exception.PaymentNotMultipleOfThousandException;
import lotto.model.exception.PaymentNotNaturalNumberException;

public class Payment {
        private static final int LOTTO_PRICE = 1000;

        private final int number;

        public Payment(int number) {
                checkPaymentNaturalNumber(number);
                checkPaymentMultipleOfThousand(number);
                this.number = number;
        }

        private void checkPaymentMultipleOfThousand(int number) {
                if (number % LOTTO_PRICE != 0) {
                        throw new PaymentNotMultipleOfThousandException("구입 금액은 1000의 배수이어야 합니다.");
                }
        }

        private void checkPaymentNaturalNumber(int number) {
                if (number <= 0) {
                        throw new PaymentNotNaturalNumberException("구입 금액은 0보다 커야합니다.");
                }
        }
}
