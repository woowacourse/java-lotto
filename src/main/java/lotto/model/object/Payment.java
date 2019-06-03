package lotto.model.object;

import lotto.model.exception.PaymentNotMultipleOfThousandException;
import lotto.model.exception.PaymentNotNaturalNumberException;

public class Payment {
        private static final int LOTTO_PRICE = 1000;

        private final int amount;

        public Payment(final int amount) {
                checkPaymentNaturalNumber(amount);
                checkPaymentMultipleOfThousand(amount);
                this.amount = amount;
        }

        public int getAmount() {
                return amount;
        }

        public double calculateYield(final int totalRevenue) {
                return (double) totalRevenue / amount;
        }

        public boolean isPurchasable(final int manualPurchaseNumber) {
                return amount / LOTTO_PRICE >= manualPurchaseNumber;
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
