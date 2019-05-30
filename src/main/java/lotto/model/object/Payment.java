package lotto.model.object;

public class Payment {
        private static final int LOTTO_PRICE = 1000;

        private final int amount;

        public Payment(final int amount) {
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
}
