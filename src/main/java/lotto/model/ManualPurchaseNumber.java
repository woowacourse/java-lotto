package lotto.model;

import lotto.model.exception.InvalidManualPurchaseNumberException;

public class ManualPurchaseNumber {
        private final int number;

        public ManualPurchaseNumber(final int number, final Payment payment) {
                checkValidManualPurchaseNumber(number, payment);
                this.number = number;
        }

        public int getNumber() {
                return number;
        }

        private static void checkValidManualPurchaseNumber(final int number, final Payment payment) {
                if (number < 0 || !payment.isPurchasable(number)) {
                        throw new InvalidManualPurchaseNumberException("수동으로 구매할 로또 수는 0이상, 총 구매 수 이하입니다.");
                }
        }
}
