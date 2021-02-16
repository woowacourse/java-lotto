package lotto.lottogame;

import lotto.money.Money;

import java.util.Objects;

public class LottoCount {
    private static final int LOTTO_PRICE = 1000;

    private final int lottoCount;

    public LottoCount(Money money) {
        this.lottoCount = money.divideMoney(LOTTO_PRICE);
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