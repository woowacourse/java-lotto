package lotto.domain;

public class Count {
    private final int manualCount;
    private final int autoCount;

    public Count(int manualCount, int autoCount) {
        checkManualCount(manualCount);
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    private void checkManualCount(int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("[ERROR] 구매할 로또 수는 음수가 될 수 없습니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
