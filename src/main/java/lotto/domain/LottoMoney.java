package lotto.domain;

import java.text.MessageFormat;
import java.util.Objects;

public class LottoMoney {

    private static final int MINIMUM_LOTTO_MONEY = 1_000;
    private static final int MAXIMUM_LOTTO_MONEY = 100_000;

    private final int value;

    private LottoMoney(int value) {
        this.value = value;
    }

    public static LottoMoney createLottoMoney(int value) {
        validateLottoMoneyRange(value);
        return new LottoMoney(value);
    }

    public static LottoMoney createMinimumLottoMoney() {
        validateLottoMoneyRange(MINIMUM_LOTTO_MONEY);
        return new LottoMoney(MINIMUM_LOTTO_MONEY);
    }

    private static void validateLottoMoneyRange(long value) {
        if (value < MINIMUM_LOTTO_MONEY || value > MAXIMUM_LOTTO_MONEY) {
            String exceptionMessage = MessageFormat.format("입력금액은 {0} 이상, {1} 이하 이어야 한다.", MINIMUM_LOTTO_MONEY, MAXIMUM_LOTTO_MONEY);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static LottoMoney createLottoMoneyByCount(int count) {
        return new LottoMoney(MINIMUM_LOTTO_MONEY * count);
    }

    public LottoMoney minus(LottoMoney money) {
        return new LottoMoney(this.value - money.value);
    }

    public boolean isGreaterThan(LottoMoney money) {
        return this.value >= money.value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoMoney)) {
            return false;
        }
        LottoMoney money1 = (LottoMoney) o;
        return value == money1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
