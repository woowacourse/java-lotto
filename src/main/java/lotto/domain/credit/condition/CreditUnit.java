package lotto.domain.credit.condition;

public enum CreditUnit {

    DIVIDING_UNIT(1000);

    private final int unit;

    CreditUnit(final int unit) {
        this.unit = unit;
    }

    public static boolean isNotDivisible(final int creditMoney) {
        return (creditMoney % DIVIDING_UNIT.unit) != 0;
    }

    public static int divide(final int creditMoney) {
        return creditMoney / DIVIDING_UNIT.unit;
    }

}
