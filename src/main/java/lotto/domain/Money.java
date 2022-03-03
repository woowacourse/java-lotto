package lotto.domain;

public class Money {
    public static final int UNIT_AMOUNT = 1000;

    private final int amount;

    public Money(int value) {
        checkShortMoney(value);
        checkDivideByUnitAmount(value);
        this.amount = value;
    }

    private void checkShortMoney(int value) {
        if (value < UNIT_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 최소 금액은 1000원입니다.");
        }
    }

    private void checkDivideByUnitAmount(int value) {
        if (value % UNIT_AMOUNT != 0) {
            throw new IllegalArgumentException("[ERROR] 금액을 1000원 단위로 입력해주세요");
        }
    }

    public Count calculateCount(int manualCount) {
        if (calculateLottoCount() < manualCount) {
            throw new IllegalArgumentException("[ERROR] 금액이 모자랍니다, 수동 구매횟수를 줄여주세요.");
        }
        return new Count(manualCount, calculateLottoCount() - manualCount);
    }

    private int calculateLottoCount() {
        return amount / UNIT_AMOUNT;
    }

    public int getAmount() {
        return amount;
    }
}
