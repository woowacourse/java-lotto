package domain;

public class PurchaseLottoCounts {
    private final int manualCount;
    private final int autoCount;

    public PurchaseLottoCounts(int manualCount, int autoCount) {
        checkCountPositive(manualCount);
        checkCountPositive(autoCount);
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    private void checkCountPositive(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("구매 개수는 양수를 입력해주세요.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
