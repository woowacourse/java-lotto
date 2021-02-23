package lotto.domain;

public class SelfLottoCount {

    private final int selfCount;

    public SelfLottoCount(final int buyCount, final int selfCount) {
        validate(buyCount, selfCount);
        this.selfCount = selfCount;
    }

    private void validate(final int buyCount, final int selfCount) {
        if (selfCount < 0 || buyCount - selfCount < 0) {
            throw new IllegalArgumentException();
        }
    }

    public int getSelfCount() {
        return selfCount;
    }
}