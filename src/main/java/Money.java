public class Money {

    private static final int UNIT = 1000;
    private final int money;

    public Money(int money) {
        if(money < UNIT ){
            throw new IllegalArgumentException();
        }
        this.money = money;
    }

    public int generateCount() {
        return money / UNIT;
    }

    public int getMoney() {
        return money;
    }
}
