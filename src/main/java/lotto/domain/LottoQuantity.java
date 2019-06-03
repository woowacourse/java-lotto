package lotto.domain;

import java.util.Objects;

public class LottoQuantity {
    public static final LottoQuantity ZERO = new LottoQuantity(0);
    private static final int MIN_QUANTITY = 0;

    private int quantity;

    private LottoQuantity(int quantity) {
        this.quantity = quantity;
        validateQuantity();
    }

    public static LottoQuantity create(int quantity) {
        return new LottoQuantity(quantity);
    }

    public static LottoQuantity create(String quantity) {
        try {
            return new LottoQuantity(Integer.parseInt(quantity));
        } catch (NumberFormatException e) {
            throw new InvalidLottoQuantityException("로또 개수는 숫자로 설정 가능합니다.");
        }
    }

    private void validateQuantity() {
        if (quantity < MIN_QUANTITY) {
            throw new InvalidLottoQuantityException(MIN_QUANTITY + "이상으로 설정 가능합니다.");
        }
    }

    public boolean biggerThan(int size) {
        return quantity > size;
    }

    public boolean biggerThan(LottoQuantity lottoQuantity) {
        return biggerThan(lottoQuantity.quantity);
    }

    public void subtract(LottoQuantity lottoQuantity) {
        quantity -= lottoQuantity.quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoQuantity that = (LottoQuantity) o;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }
}
