package lotto.domain;

public class Count {

    private final int manualCount;
    private final int autoCount;

    public Count(int manualCount, int autoCount) {
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
