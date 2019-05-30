package lotto.model.creator;

import lotto.model.exception.PaymentNotMultipleOfThousandException;
import lotto.model.exception.PaymentNotNaturalNumberException;
import lotto.model.object.Payment;

public class PaymentCreator {
        private static final int LOTTO_PRICE = 1000;

        public static Payment create(final int amount) {
                checkPaymentNaturalNumber(amount);
                checkPaymentMultipleOfThousand(amount);
                return new Payment(amount);
        }

        private static void checkPaymentMultipleOfThousand(final int amount) {
                if (amount % LOTTO_PRICE != 0) {
                        throw new PaymentNotMultipleOfThousandException("구입 금액은 1000의 배수이어야 합니다.");
                }
        }

        private static void checkPaymentNaturalNumber(final int amount) {
                if (amount <= 0) {
                        throw new PaymentNotNaturalNumberException("구입 금액은 0보다 커야합니다.");
                }
        }
}
