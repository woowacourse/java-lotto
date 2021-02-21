package lottogame.domain;

public class Money {

    private int value;

    public Money(final String money) {
        this.value = Integer.parseInt(money);
        moneyValidate(this.value);
    }

    public void use(final int price) {
        moneyValidate(price);
        this.value -= price;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isCanBuy(final int price) {
        return value > price;
    }

    private void moneyValidate(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈은 음수가 될 수 없습니다.");
        }
    }
}
