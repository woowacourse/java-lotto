package com.woowacourse.lotto.domain;

import java.util.Objects;

public class WinningLotto {
    private final LottoNumberGroup winningNums;
    private final LottoNumber bonus;

    public WinningLotto(LottoNumberGroup winningNums, LottoNumber bonus) {
        this.winningNums = winningNums;
        this.bonus = bonus;
        if (winningNums.contains(bonus)) {
            throw new IllegalArgumentException("보너스 볼은 다른 숫자와 겹칠 수 없습니다.");
        }
    }

    public LottoResult match(Lotto lotto) {
        return LottoResult.valueOf(lotto.countMatch(winningNums), lotto.contains(bonus));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningNums, that.winningNums) &&
            Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNums, bonus);
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("WinningLotto { winningNums: ")
            .append(winningNums)
            .append("bonus: ")
            .append(bonus)
            .toString();

    }
}
