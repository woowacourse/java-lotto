package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int LOTTO_PRICE = 1_000;

    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOTTO_PRICE + "원 이상의 금액을 입력하세요");
        }
    }

    public void checkManualSize(int manualSize) {
        if (manualSize > getLottoSize() && manualSize < 0) {
            throw new IllegalArgumentException("수동 구매 장수는 0 이상" + getLottoSize() + "이하 입니다.");
        }
    }

    public int getLottoSize() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
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
}
