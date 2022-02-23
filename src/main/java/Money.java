public class Money {

    private static final String MIN_UNIT_MESSAGE = "[ERROR] 구입 금액은 1000원 단위이어야 합니다.";
    private static final int UNIT = 1000;

    private final int money;

    public Money(int money) {
        validateUnit(money);
        this.money = money;
    }

    public int generateCount() {
        return money / UNIT;
    }

    public int getMoney() {
        return money;
    }

    private void validateUnit(int money) {
        if (money < UNIT) {
            throw new IllegalArgumentException(MIN_UNIT_MESSAGE);
        }
    }
}
