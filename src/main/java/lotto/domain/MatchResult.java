package lotto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum MatchResult {
    THREE_MATCH(3, false, 5_000),
    FOUR_MATCH(4, false, 50_000),
    FIVE_MATCH(5, false, 1_500_000),
    FIVE_MATCH_WITH_BONUS_BALL(5, true, 30_000_000),
    SIX_MATCH(6, false, 2_000_000_000),
    NONE(0, false, 0);

    private static final int BALLS_COUNT = 6;
    private static final int WINNING_BALLS_COUNT = 6;

    private int matchCount;
    private boolean isBonus;
    private int prize;

    MatchResult(int matchCount, boolean isBonus, int prize) {
        this.matchCount = matchCount;
        this.isBonus = isBonus;
        this.prize = prize;
    }

    public static MatchResult findMatchResult(Lotto lotto, WinningLotto winningLotto) {
        int sameNumberCount = countsOfDuplicates(lotto, winningLotto.getWinningBalls());
        boolean isBonus = isFiveMatchWithBonusBall(sameNumberCount, lotto, winningLotto.getBonusBall());
        return MatchResult.of(sameNumberCount, isBonus);
    }

    private static int countsOfDuplicates(Lotto lotto, WinningBalls winningBalls) {
        Set<Ball> numbers = new HashSet<>(lotto.getBalls());
        numbers.addAll(winningBalls.getWinningBalls());
        int differentNumbersCount = numbers.size();
        return BALLS_COUNT + WINNING_BALLS_COUNT - differentNumbersCount;
    }

    private static boolean isFiveMatchWithBonusBall(int sameNumberCount, Lotto lotto, BonusBall bonusBall) {
        return sameNumberCount == FIVE_MATCH.getMatchCount() && bonusBall.hasIncluded(lotto.getBalls());
    }

    static MatchResult of(int matchCount, boolean isBonus) {
        return Arrays.stream(values())
                .filter(v -> matchCount == v.matchCount && isBonus == v.isBonus)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
