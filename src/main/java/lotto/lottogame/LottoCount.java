package lotto.lottogame;

import lotto.money.Money;

import java.util.Objects;

public class LottoCount {
    public static final int ZERO = 0;
    public static final int ONE_COUNT = 1;
    public static final int LOTTO_PRICE = 1000;

    private final int lottoCount;

    public LottoCount(int lottoCount) {
        this.lottoCount = lottoCount;
    }

    public static LottoCount valueOf(Money money) {
        return new LottoCount(money.divideMoney(LOTTO_PRICE));
    }

    public boolean isGreaterThanZero() {
        return this.lottoCount > ZERO;
    }

    public LottoCount decreaseOne() {
        return new LottoCount(lottoCount - ONE_COUNT);
    }

    public int getLottoCount() {
        return lottoCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoCount that = (LottoCount) o;
        return lottoCount == that.lottoCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoCount);
    }
}