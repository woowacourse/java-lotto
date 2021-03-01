package lotto.domain;

import java.util.Objects;

import static lotto.domain.Money.PRICE_OF_LOTTO;

public class Quantity {

    public static final int ZERO_COUNT = 0;

    private final int quantity;

    public Quantity(String quantity) {
        this(changeToInt(quantity));
    }

    public Quantity(Money money) {
        this(money.countLotto());
    }

    public Quantity(int quantity) {
        validateNegative(quantity);
        this.quantity = quantity;
    }

    public static int changeToInt(String quantity) {
        validateEmpty(quantity);
        validateNumber(quantity);
        return Integer.parseInt(quantity);
    }

    public int quantity() {
        return quantity;
    }

    public Money calculateQuantityPrice() {
        return new Money(quantity * PRICE_OF_LOTTO);
    }

    private static void validateEmpty(String quantity) {
        if (quantity.isEmpty()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.");
        }
    }

    private static void validateNumber(String quantity) {
        try {
            Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    private void validateNegative(int quantity) {
        if (quantity < ZERO_COUNT) {
            throw new IllegalArgumentException("로또 구매 수는 양수여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity1 = (Quantity) o;
        return quantity == quantity1.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }
}
