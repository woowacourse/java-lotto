package lotto.domain;

import java.util.Objects;

public class Money {

    public static final int PRICE_OF_LOTTO = 1000;

    private final int money;

    public Money(String money) {
        this(changeToInt(money));
    }

    public Money(int money) {
        validateNegative(money);
        this.money = money;
    }

    private static int changeToInt(String money) {
        validateEmpty(money);
        validateNumber(money);
        return Integer.parseInt(money);
    }

    public int money() {
        return money;
    }

    public int countLotto() {
        return money / PRICE_OF_LOTTO;
    }

    public Money calculateSubtract(Money money) {
        if (this.money() - money.money() < 0) {
            throw new IllegalArgumentException("금액이 충분하지 않습니다.");
        }
        return new Money(this.money() - money.money());
    }


    private static void validateEmpty(String money) {
        if (money.isEmpty()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.");
        }
    }

    private static void validateNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    private void validateNegative(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("금액은 양수여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return "money : " + money;
    }
}
