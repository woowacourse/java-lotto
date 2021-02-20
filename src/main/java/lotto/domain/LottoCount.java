package lotto.domain;

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
}
