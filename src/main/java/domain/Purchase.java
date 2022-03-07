package domain;

public class Purchase {

    private static final int MONEY_UNIT = 1000;

    private final int money;
    private final int autoCount;
    private final int manualCount;

    public Purchase(int money, int manualCount) {
        this.money = money;
        this.manualCount = manualCount;
        this.autoCount = money / MONEY_UNIT - manualCount;
    }

    public int getMoney() {
        return money;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }
}