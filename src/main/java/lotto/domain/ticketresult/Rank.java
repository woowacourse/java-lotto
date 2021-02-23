package lotto.domain.ticketresult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000);

    private final int matchedNumberCount;
    private final int prizeMoney;

    Rank(int matchedNumberCount, int prizeMoney) {
        this.matchedNumberCount = matchedNumberCount;
        this.prizeMoney = prizeMoney;
    }

    public int getCountMatchedNumbers() {
        return matchedNumberCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static List<Rank> getLottoMatchType(int matchedNumberCount) {
        return Arrays.stream(Rank.values())
                .filter(lottoMatchType -> lottoMatchType.matchedNumberCount == matchedNumberCount)
                .collect(Collectors.toList());
    }
}
