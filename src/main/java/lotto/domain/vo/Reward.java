package lotto.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Reward {

    private static final int MINIMUM_REWARD = 0;
    private static final int DECIMAL_PLACE = 2;

    private final long value;

    public Reward(long value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(long value) {
        if (value < MINIMUM_REWARD) {
            throw new IllegalArgumentException("상금은 0이상이어야 한다.");
        }
    }

    public Reward plus(Reward reward) {
        return new Reward(this.value + reward.value);
    }

    public BigDecimal divide(LottoMoney money) {
        return BigDecimal.valueOf(this.value).divide(BigDecimal.valueOf(money.getValue()), DECIMAL_PLACE, RoundingMode.DOWN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reward)) return false;
        Reward reward = (Reward) o;
        return value == reward.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public long getValue() {
        return this.value;
    }
}
