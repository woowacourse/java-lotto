package lottogame.domain.stats;

import java.util.Objects;
import java.util.regex.Pattern;

public class Quantity {
    private static final Pattern QUANTITY_PATTERN = Pattern.compile("^(0|[1-9][0-9]*)$");
    private static final int LOTTO_PRICE = 1000;
    private final int quantity;

    private Quantity(int quantity) {
        this.quantity = quantity;
    }

    private static Quantity of(int quantity) {
        validate(quantity);
        return new Quantity(quantity);
    }

    public static Quantity of(String quantity) {
        validate(quantity);
        return Quantity.of(Integer.parseInt(quantity));
    }

    private static void validate(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("잘못된 로또 수입니다.");
        }
    }

    private static void validate(String quantity) {
        if (!QUANTITY_PATTERN.matcher(quantity).matches()) {
            throw new IllegalArgumentException("잘못된 로또 수입니다.");
        }
    }

    public static Quantity from(Money money, Quantity manualQuantity) {
        Quantity totalQuantity = Quantity.of(money.value() / LOTTO_PRICE);
        return totalQuantity.subtract(manualQuantity);
    }

    private Quantity subtract(Quantity quantity) {
        return Quantity.of(this.quantity - quantity.quantity);
    }

    public boolean isZero() {
        return quantity == 0;
    }

    public int value() {
        return quantity;
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
