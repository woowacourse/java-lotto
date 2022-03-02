package lotto.utils;

public enum MoneyUnit {

    DEFAULT_UNIT(1000);

    private final int unit;

    MoneyUnit(final int unit) {
        this.unit = unit;
    }

    public static boolean isNotDivisible(final int money) {
        return (money % DEFAULT_UNIT.unit) != 0;
    }

    public static int divide(final int money) {
        return money / DEFAULT_UNIT.unit;
    }

    public int getUnit() {
        return this.unit;
    }

}
