package lotto.model.creator;

import lotto.model.exception.PaymentNotMultipleOfThousandException;
import lotto.model.exception.PaymentNotNaturalNumberException;
import lotto.model.object.Payment;

public class PaymentCreator {
        private static final int LOTTO_PRICE = 1000;

        public static Payment create(int number) {
                checkPaymentNaturalNumber(number);
                checkPaymentMultipleOfThousand(number);
                return new Payment(number);
        }

        private static void checkPaymentMultipleOfThousand(int number) {
                if (number % LOTTO_PRICE != 0) {
                        throw new PaymentNotMultipleOfThousandException("구입 금액은 1000의 배수이어야 합니다.");
                }
        }

        private static void checkPaymentNaturalNumber(int number) {
                if (number <= 0) {
                        throw new PaymentNotNaturalNumberException("구입 금액은 0보다 커야합니다.");
                }
        }
}
