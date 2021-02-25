package lottogame.domain.stats;

import lottogame.utils.InvalidMoneyException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Money {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]{3,}$");
    private final int money;

    public Money(String money) {
        validate(money);
        this.money = Integer.parseInt(money);
    }

    public int getMoney() {
        return this.money;
    }

    private void validate(String money) {
        if (!NUMBER_PATTERN.matcher(money).matches()) {
            throw new InvalidMoneyException();
        }
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
