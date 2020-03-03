package lotto.domain;

import lotto.exception.InvalidInputException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {
    public static final int BALLS_COUNT = 6;
    private static final int WINNING_BALLS_COUNT = 6;
    private static final String COMMA = ",";

    private final List<Ball> balls;

    public Lotto(String winningLotto) {
        this(Arrays.stream(winningLotto.split(COMMA))
                .map(String::trim)
                .map(Ball::new)
                .collect(Collectors.toList()));
    }

    public Lotto(List<Ball> balls) {
        checkEmpty(balls);
        checkCount(balls);
        checkDuplicate(balls);
        this.balls = balls;
    }

    private void checkEmpty(List<Ball> balls) {
        if (balls.isEmpty()) {
            throw new InvalidInputException("Lotto 내부의 Ball이 존재하지 않습니다.");
        }
    }

    private void checkCount(List<Ball> balls) {
        if (balls.size() != BALLS_COUNT) {
            throw new InvalidInputException(String.format("로또 내부의 번호 개수는 %d개만 가능합니다.", BALLS_COUNT));
        }
    }

    private void checkDuplicate(List<Ball> balls) {
        Set<Ball> result = new HashSet<>(balls);
        if (result.size() != BALLS_COUNT) {
            throw new InvalidInputException("중복되는 숫자가 존재합니다.");
        }
    }

    public Stream<Ball> stream() {
        return this.balls.stream();
    }

    public MatchResult findMatchResult(WinningNumbers winningNumbers) {
        int sameNumberCount = countsOfDuplicates(winningNumbers.getWinningLotto());
        boolean isBonus = isFiveMatchWithBonusBall(sameNumberCount, winningNumbers.getBonusBall());
        return MatchResult.of(sameNumberCount, isBonus);
    }

    public int countsOfDuplicates(Lotto lotto) {
        Set<Ball> balls = new HashSet<>(this.balls);
        balls.addAll(lotto.getBalls());
        int differentNumbersCount = balls.size();
        return BALLS_COUNT + WINNING_BALLS_COUNT - differentNumbersCount;
    }

    private boolean isFiveMatchWithBonusBall(int sameNumberCount, Ball bonusBall) {
        return sameNumberCount == MatchResult.FIVE_MATCH.getMatchCount() && contains(bonusBall);
    }

    public boolean contains(Ball bonusBall) {
        return this.balls.stream()
                .anyMatch(ball -> ball.equals(bonusBall));
    }

    public List<Ball> getBalls() {
        return Collections.unmodifiableList(balls);
    }
}
