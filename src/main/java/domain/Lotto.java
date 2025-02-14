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

    public MatchDto getMatchDto(List<Integer> winningNumbers, Ball bonus) {
        int winningNumberCount = balls.stream()
                .filter(winningNumbers::contains)
                .mapToInt(e -> 1)
                .sum();

        boolean hasBonusNumber = balls.contains(bonus);

        return new MatchDto(
                winningNumberCount,
                hasBonusNumber
        );
    }

    public String getBalls() {
        return balls.toString();
    }

}
