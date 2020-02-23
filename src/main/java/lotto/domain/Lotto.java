package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int BALLS_COUNT = 6;
    private static final int WINNING_BALLS_COUNT = 6;
    private static final int FIVE_MATCH = 5;

    private final List<Ball> balls;

    public Lotto(List<Ball> balls) {
        this.balls = balls;
    }

    public MatchResult findMatchResult(WinningBalls winningBalls, BonusBall bonusBall) {
        int sameNumberCount = calculateSameNumberCountWith(winningBalls);
        if (sameNumberCount == FIVE_MATCH && bonusBall.isIncluded(balls)) {
            return MatchResult.FIVE_MATCH_WITH_BONUS_BALL;
        }
        return MatchResult.of(sameNumberCount);
    }

    private int calculateSameNumberCountWith(WinningBalls winningBalls) {
        Set<Ball> numbers = new HashSet<Ball>(this.balls);
        numbers.addAll(winningBalls.getWinningBalls());
        int differentNumbersCount = numbers.size();
        return BALLS_COUNT + WINNING_BALLS_COUNT - differentNumbersCount;
    }

    public List<Ball> getBalls() {
        return Collections.unmodifiableList(balls);
    }
}
