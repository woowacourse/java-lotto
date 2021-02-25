package lotto.lottogame;

import java.util.Objects;

public class LottoCount {
    public static final int ZERO = 0;
    public static final int ONE_COUNT = 1;
    public static final int LOTTO_PRICE = 1000;

    private int lottoCount;

    public LottoCount(int value) {
        this.lottoCount = value;
    }

    public static LottoCount valueOf(LottoCount lottoCount) {
        return new LottoCount(lottoCount.lottoCount);
    }

    public boolean isBiggerThanZeroWithDecreasing() {
        return this.lottoCount-- > ZERO;
    }

    public boolean isSmallerThan(int manualLottoCount) {
        return lottoCount < manualLottoCount;
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