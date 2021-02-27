package domain;

import java.util.Objects;

public class CountOfPurchasingLotto {

    private int value;

    public CountOfPurchasingLotto(int value) {
        this.value = value;
    }

    public void purchaseLotto() {
        value--;
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
