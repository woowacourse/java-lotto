package domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int money() {
        return this.money;
    }

    public int convertToAmount() {
        return money / LOTTO_PRICE;

    }


}
