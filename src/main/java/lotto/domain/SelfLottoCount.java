package lotto.domain;

import lotto.exception.InvalidSelfLottoCountException;

public class SelfLottoCount {

    private final int selfCount;

    public SelfLottoCount(final int buyCount, final int selfCount) {
        validate(buyCount, selfCount);
        this.selfCount = selfCount;
    }

    private void validate(final int buyCount, final int selfCount) {
        if (selfCount < 0 || buyCount - selfCount < 0) {
            throw new InvalidSelfLottoCountException();
        }
    }

    public int getSelfCount() {
        return selfCount;
    }
}