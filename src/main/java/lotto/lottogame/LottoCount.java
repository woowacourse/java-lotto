package lotto.lottogame;

import lotto.money.Money;

import java.util.Objects;

public class LottoCount {
    public static final int ZERO = 0;
    public static final int ONE_COUNT = 1;
    public static final int LOTTO_PRICE = 1000;

    private int lottoCount;

    public LottoCount(Money money) {
        this.lottoCount = money.divideMoney(LOTTO_PRICE);
    }

    public boolean isBiggerThanZeroWithDecreasing() {
        return this.lottoCount-- > ZERO;
    }

    public boolean isSmallerThan(int autoLottoCount) {
        return lottoCount < autoLottoCount;
    }

    public int subtractCount(int manualLottoCount) {
        return lottoCount - manualLottoCount;
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