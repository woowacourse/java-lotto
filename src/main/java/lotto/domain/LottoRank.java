package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum LottoRank {

    FIRST(6, BonusBallState.EITHER, 2_000_000_000),
    SECOND(5, BonusBallState.INCLUDE, 30_000_000),
    THIRD(5, BonusBallState.DOES_NOT_INCLUDE, 1_500_000),
    FOURTH(4, BonusBallState.EITHER, 50_000),
    FIFTH(3, BonusBallState.EITHER, 5_000),
    FAILED(0, BonusBallState.EITHER, 0);

    private final int winningNumberMatchCount;
    private final BonusBallState bonusBallState;
    private final long prizeMoney;

    LottoRank(int winningNumberMatchCount, BonusBallState bonusBallState, long prizeMoney) {
        this.winningNumberMatchCount = winningNumberMatchCount;
        this.bonusBallState = bonusBallState;
        this.prizeMoney = prizeMoney;
    }

    public enum BonusBallState {
        INCLUDE(true),
        DOES_NOT_INCLUDE(false),
        EITHER(true, false);

        private final Boolean[] bonusBallIncludeStates;

        BonusBallState(Boolean... bonusBallIncludeStates) {
            this.bonusBallIncludeStates = bonusBallIncludeStates;
        }

        public boolean contains(boolean containsBonusBall) {
            return List.of(bonusBallIncludeStates)
                    .contains(containsBonusBall);
        }
    }

    public int winningNumberMatchCount() {
        return winningNumberMatchCount;
    }

    public long prizeMoney() {
        return prizeMoney;
    }

    public static LottoRank getRank(int winningNumberCount, boolean containsBonusBall) {
        return Arrays.stream(values())
                .filter(classifyRank(winningNumberCount, containsBonusBall))
                .findFirst()
                .orElseGet(() -> FAILED);
    }

    private static Predicate<LottoRank> classifyRank(int winningNumberCount, boolean containsBonusBall) {
        return (LottoRank lottoRank) ->
                lottoRank.winningNumberMatchCount == winningNumberCount
                        && lottoRank.bonusBallState.contains(containsBonusBall);
    }
}
