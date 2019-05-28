package com.woowacourse.lotto.domain;

import java.util.Arrays;

public enum LottoResult {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean useBonus;
    private final int prizeMoney;

    LottoResult(int matchCount, boolean useBonus, int prizeMoney) {
        this.matchCount = matchCount;
        this.useBonus = useBonus;
        this.prizeMoney = prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isUseBonus() {
        return useBonus;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static LottoResult valueOf(int matchCount, boolean matchBonus) {
        if (matchBonus && matchCount == SECOND.matchCount) {
            return SECOND;
        }

        return Arrays.stream(values())
            .filter(r -> r.matchCount == matchCount && !r.useBonus)
            .findFirst()
            .orElse(NONE);
    }
}
