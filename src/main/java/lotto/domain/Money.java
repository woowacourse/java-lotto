package lotto.domain;

public class Money {

    private int money;

    public Money(String money){
        validateNumber(money);
        validateMoneyLowerThan1000(money);
        this.money = Integer.parseInt(money);
    }

    private void validateNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 정수만 입력 가능합니다");
        }
    }

    private void validateMoneyLowerThan1000(String money) {
        if (Integer.parseInt(money) < 1000) {
            throw new IllegalArgumentException("1000원 이상의 금액만 입력 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;

        Money money1 = (Money) o;

        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return money;
    }
}
