import java.math.BigInteger;
import java.util.Objects;

public class Prize {
    public static final Prize ZERO_PRIZE = new Prize(BigInteger.ZERO);

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
