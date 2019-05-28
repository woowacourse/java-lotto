package com.woowacourse.lotto.domain;

import java.util.Objects;
import java.util.Set;

public class WinningLotto {
    private final Set<Integer> winningNums;
    private final int bonus;

    public WinningLotto(Set<Integer> winningNums, int bonus) {
        this.winningNums = winningNums;
        this.bonus = bonus;
        if (winningNums.contains(bonus)) {
            throw new IllegalArgumentException("보너스 볼은 다른 숫자와 겹칠 수 없습니다.");
        }
    }

    public LottoResult match(Lotto lotto) {
        return LottoResult.valueOf((int) winningNums.stream()
            .filter(lotto::contains)
            .count(), lotto.contains(bonus));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return bonus == that.bonus &&
            Objects.equals(winningNums, that.winningNums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNums, bonus);
    }
}
