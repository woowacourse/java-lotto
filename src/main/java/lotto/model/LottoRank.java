package lotto.model;

import static java.util.List.of;

import java.util.Arrays;
import java.util.List;

public enum LottoRank {
    FIRST(6, of(true, false), 2_000_000_000),
    SECOND(5, of(true), 30_000_000),
    THIRD(5, of(false), 1_500_000),
    FOURTH(4, of(true, false), 50_000),
    FIFTH(3, of(true, false), 5_000),
    FAILED(0, of(true, false), 0);

    private final int winningNumberCount;
    private final List<Boolean> bonusBallBooleans;
    private final long prizeMoney;

    LottoRank(int winningNumberCount, List<Boolean> bonusBallBooleans, long prizeMoney) {
        this.winningNumberCount = winningNumberCount;
        this.bonusBallBooleans = bonusBallBooleans;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRank getRank(int winningNumberCount, boolean containsBonusBall) {
        return Arrays.stream(values())
                .filter((LottoRank lottoRank) ->
                        lottoRank.winningNumberCount == winningNumberCount
                                && lottoRank.bonusBallBooleans.contains(containsBonusBall))
                .findFirst()
                .orElseGet(() -> FAILED);
    }

    public int getWinningNumberCount() {
        return winningNumberCount;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
