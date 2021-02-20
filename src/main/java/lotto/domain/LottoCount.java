package lotto.domain;

import java.util.Objects;

public class LottoCount {

    private static final int MIN_COUNT = 0;
    private final int count;

    public LottoCount(int count) {
        validateCount(count);
        this.count = count;
    }

    public int get() {
        return count;
    }

    private void validateCount(int count) {
        if (count < MIN_COUNT) {
            throw new IllegalArgumentException("0 이상의 숫자를 입력해야 합니다.");
        }
    }

    public LottoCount subtract(LottoCount inputLottoCount) {
        return new LottoCount(count - inputLottoCount.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
