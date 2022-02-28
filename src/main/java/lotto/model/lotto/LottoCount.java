package lotto.model.lotto;

import java.util.Objects;

import lotto.utils.ConverterUtils;

public class LottoCount {
    private static final int END = 0;
    private static final int UNIT = 1000;

    private int count;

    public LottoCount(String money) {
        this.count = makeLottoCount(money);
    }

    private int makeLottoCount(String money) {
        return ConverterUtils.convertStringToInt(money) / UNIT;
    }

    public boolean isZero() {
        return count == END;
    }

    public void makeLotto() {
        count--;
    }

    public int getCount() {
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
