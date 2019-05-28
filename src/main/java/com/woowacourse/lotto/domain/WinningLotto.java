package com.woowacourse.lotto.domain;

import java.util.Objects;
import java.util.Set;

public class WinningLotto {
    private final Set<Integer> winningNums;

    public WinningLotto(Set<Integer> winningNums) {
        this.winningNums = winningNums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningNums, that.winningNums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNums);
    }

    public LottoResult match(Lotto lotto) {
        return LottoResult.valueOf((int)winningNums.stream()
            .filter(lotto::contains)
            .count(), false);
    }
}
