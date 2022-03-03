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
        validateLottoNumberRange(value);
        return new LottoMoney(value);
    }

    public static LottoMoney createMinimumLottoMoney() {
        validateLottoNumberRange(MINIMUM_LOTTO_MONEY);
        return new LottoMoney(MINIMUM_LOTTO_MONEY);
    }

    public static LottoMoney createLottoMoneyByCount(int count) {
        validateCountPositive(count);
        validateLottoOverRange((long) MINIMUM_LOTTO_MONEY * count);
        return new LottoMoney(MINIMUM_LOTTO_MONEY * count);
    }

    private static void validateLottoNumberRange(long value) {
        validateLottoOverRange(value);
        validateLottoUnderRange(value);
    }

    private static void validateLottoUnderRange(long value) {
        if (value < MINIMUM_LOTTO_MONEY) {
            String exceptionMessage = MessageFormat.format("입력금액은 {0} 이상 이어야 한다.", MINIMUM_LOTTO_MONEY);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private static void validateLottoOverRange(long value) {
        if (value > MAXIMUM_LOTTO_MONEY) {
            String exceptionMessage = MessageFormat.format("입력금액은 {0} 이하 이어야 한다.", MAXIMUM_LOTTO_MONEY);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private static void validateCountPositive(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("로또 구매 개수는 음수일 수 없다.");
        }
    }

    public LottoMoney minus(LottoMoney money) {
        return new LottoMoney(this.value - money.value);
    }

    public void validateCanBuyLotto(int lottoCount) {
        if (this.value < LottoMoney.createLottoMoneyByCount(lottoCount).getValue()) {
            throw new IllegalArgumentException("로또를 구매할 돈이 부족하다.");
        }
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
