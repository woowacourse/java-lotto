import java.math.BigInteger;
import java.util.Objects;

public class Prize {
    public static final Prize ZERO = new Prize(BigInteger.ZERO);

    private final BigInteger amount;

    public Prize(int amount) {
        this.amount = BigInteger.valueOf(amount);
    }

    private Prize(BigInteger amount) {
        this.amount = amount;
    }

    public Prize add(Prize prize) {
        return new Prize(this.amount.add(prize.amount));
    }

    public Prize multiply(int factor) {
        return new Prize(this.amount.multiply(BigInteger.valueOf(factor)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Prize prize = (Prize) o;
        return Objects.equals(amount, prize.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
