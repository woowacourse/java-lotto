package lotto.model.lotto;

import java.util.Objects;

public class LottoCount {
    private static final int END = 0;
    private static final int UNIT = 1000;

    private int count;

    public LottoCount(long money, int count) {
        this.count = makeLottoCount(money, count);
    }

    private int makeLottoCount(long money, int count) {
        return (int) ((money / UNIT) - count);
    }

    boolean isZero() {
        return count == END;
    }

    void makeLotto() {
        this.count--;
    }

    long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
