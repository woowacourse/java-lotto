package domain;

import java.util.ArrayList;
import java.util.List;


public class Lotto {

    public static final int LOTTO_PRICE = 1000;
    public static final int MAX_LOTTO_SIZE = 6;

    private List<Ball> balls;

    public Lotto(List<Integer> numbers) {
        List<Ball> balls = new ArrayList<>();
        numbers.stream()
                .map(Ball::new)
                .forEach(balls::add);
        this.balls = balls;
    }

    public MatchDto getMatchDto(WinningLotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getWinningNumbers();

        List<Integer> ballsNumber = balls.stream()
                .map(Ball::getNumber)
                .toList();

        int winningNumberCount = ballsNumber.stream()
                .filter(winningNumbers::contains)
                .mapToInt(e -> 1)
                .sum();

        boolean hasBonusNumber = ballsNumber.contains(winningLotto.getBonusNumber());

        return new MatchDto(
                winningNumberCount,
                hasBonusNumber
        );
    }

    public String getBallsString() {
        return balls.toString();
    }

    public List<Ball> getBalls() {
        return balls;
    }
}
