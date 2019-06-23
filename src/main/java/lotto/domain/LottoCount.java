package lotto.domain;

import java.util.Objects;

public class LottoCount {
    private int count;

    public LottoCount(Money money, int count) {
        if (!money.canBuyLotto(count)) {
            throw new IllegalArgumentException("금액이 부족합니다.");
        }
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public boolean isZero() {
        return getCount() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoCount)) return false;
        LottoCount that = (LottoCount) o;
        return getCount() == that.getCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCount());
    }
}
