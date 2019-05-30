package lotto.model.creator;

import lotto.model.exception.InvalidManualPurchaseNumberException;
import lotto.model.object.ManualPurchaseNumber;
import lotto.model.object.Payment;

public class ManualPurchaseNumberCreator {
        public static ManualPurchaseNumber create(final int manualPurchaseNumber, final Payment payment) {
                checkValidManualPurchaseNumber(manualPurchaseNumber, payment);
                return new ManualPurchaseNumber(manualPurchaseNumber);
        }

        private static void checkValidManualPurchaseNumber(final int manualPurchaseNumber, final Payment payment) {
                if (manualPurchaseNumber < 0 || !payment.isPurchasable(manualPurchaseNumber)) {
                        throw new InvalidManualPurchaseNumberException("수동으로 구매할 로또 수는 0이상, 총 구매 수 이하입니다.");
                }
        }
}
