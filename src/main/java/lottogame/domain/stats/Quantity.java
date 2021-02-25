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

    public static Quantity ofInt(int quantity) {
        validateInt(quantity);
        return new Quantity(quantity);
    }

    public static Quantity ofString(String quantity) {
        validateString(quantity);
        return new Quantity(Integer.parseInt(quantity));
    }

    private static void validateInt(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("잘못된 로또 수입니다.");
        }
    }

    private static void validateString(String quantity) {
        if (!QUANTITY_PATTERN.matcher(quantity).matches()) {
            throw new IllegalArgumentException("잘못된 로또 수입니다.");
        }
    }

    public static Quantity from(Money money, Quantity manualQuantity) {
        return Quantity.ofInt(money.getMoney() / LOTTO_PRICE).subtract(manualQuantity);
    }

    public int value() {
        return quantity;
    }

    public Quantity subtract(Quantity quantity) {
        return Quantity.ofInt(this.quantity - quantity.value());
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

    public boolean isZero() {
        return quantity == 0;
    }
}
