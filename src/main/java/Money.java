public class Money {

    public static final int UNIT = 1000;
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int generateCount() {
        return money / UNIT;
    }
}
