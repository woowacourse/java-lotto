package lotto.domain;

import lotto.exception.LottoException;

import java.util.Arrays;

public enum LottoRank {
    NONE(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private static final int MIN_MATCH = 3;

    private final int matchCount;
    private final int winningMoney;

    LottoRank(int matchCount, int winningMoney) {
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
    }

    public static LottoRank of(int matchCount, boolean bonusMatch) {
        if (matchCount < MIN_MATCH) {
            return NONE;
        }

        if (SECOND.matchCount == matchCount && bonusMatch) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(value -> value.matchCount == matchCount)
                .findFirst()
                .orElseThrow(() -> new LottoException("값이 잘못되었습니다."));
    }

    public int winningMoney() {
        return winningMoney;
    }

    public int matchCount() {
        return matchCount;
    }
}
