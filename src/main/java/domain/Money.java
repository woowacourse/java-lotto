package domain;

public class Money {
    private int money;

    public Money(int money){
        validateRange(money);
        this.money = money;
    }

    public int calculateTotalLotto() {
        return money / 1000;
    }

    private void validateRange(int money) {
        if(money < 0) {
            throw new IllegalArgumentException("금액은 음수가 불가능 합니다.");
        }
    }
}
