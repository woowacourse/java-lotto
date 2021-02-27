package domain;

import java.util.Objects;

public class CountOfPurchasingLotto {
    private static final int ZERO = 0;

    private int value;

    public CountOfPurchasingLotto(int value) {
        validateCountOfPurchasingLotto(value);
        this.value = value;
    }

    private void validateCountOfPurchasingLotto(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("로또 개수는 음수일 수 없습니다.");
        }
    }

    public void purchaseLotto() {
        value--;
    }

    public boolean isPurchasingLottoLeft() {
        return value > ZERO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountOfPurchasingLotto that = (CountOfPurchasingLotto) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
