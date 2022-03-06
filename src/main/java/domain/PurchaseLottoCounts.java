package domain;

public class PurchaseLottoCounts {
    private final int manualCount;
    private final int autoCount;

    public PurchaseLottoCounts(int manualCount, Amount amount) {
        checkCountRange(manualCount, amount);
        this.manualCount = manualCount;
        this.autoCount = amount.getTicketCount() - manualCount;
    }

    private void checkCountRange(int count, Amount amount) {
        if (count < 0 || count > amount.getTicketCount()) {
            throw new IllegalArgumentException("구매 개수는 0이상 " + amount.getTicketCount() + "이하여야합니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
