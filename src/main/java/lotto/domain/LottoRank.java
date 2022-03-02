package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum LottoRank {

    FIRST(6, BonusBallState.IRRELEVANT, 2_000_000_000L),
    SECOND(5, BonusBallState.SHOULD_EXIST, 30_000_000L),
    THIRD(5, BonusBallState.SHOULD_NOT_EXIST, 1_500_000L),
    FOURTH(4, BonusBallState.IRRELEVANT, 50_000L),
    FIFTH(3, BonusBallState.IRRELEVANT, 5_000L),
    NONE(0, BonusBallState.IRRELEVANT, 0L);

    private final int hitCount;
    private final BonusBallState bonusBallState;
    private final long prizeMoney;

    LottoRank(int hitCount, BonusBallState bonusBallState, long prizeMoney) {
        this.hitCount = hitCount;
        this.bonusBallState = bonusBallState;
        this.prizeMoney = prizeMoney;
    }

    public enum BonusBallState {
        SHOULD_EXIST(true),
        SHOULD_NOT_EXIST(false),
        IRRELEVANT(true, false);

        private final Boolean[] bonusBallIncludeStates;

        BonusBallState(Boolean... bonusBallIncludeStates) {
            this.bonusBallIncludeStates = bonusBallIncludeStates;
        }

        public boolean contains(boolean containsBonusBall) {
            return List.of(bonusBallIncludeStates)
                    .contains(containsBonusBall);
        }
    }

    public int hitCount() {
        return hitCount;
    }

    public long prizeMoney() {
        return prizeMoney;
    }

    public static LottoRank getRank(int hitCount, boolean containsBonusBall) {
        return Arrays.stream(values())
                .filter(classifyRank(hitCount, containsBonusBall))
                .findFirst()
                .orElse(NONE);
    }

    private static Predicate<LottoRank> classifyRank(int winningNumberCount, boolean containsBonusBall) {
        return (LottoRank lottoRank) ->
                lottoRank.hitCount == winningNumberCount
                        && lottoRank.bonusBallState.contains(containsBonusBall);
    }
}
