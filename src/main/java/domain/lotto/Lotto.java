package domain.lotto;

import java.util.ArrayList;
import java.util.List;


public class Lotto {

    public static final int LOTTO_PRICE = 1000;
    public static final int MAX_LOTTO_SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final List<Ball> balls;

    public Lotto(List<Integer> numbers) {
        List<Ball> balls = new ArrayList<>();
        numbers.stream()
                .map(Ball::new)
                .forEach(balls::add);
        validateUniqueBall(numbers);
        this.balls = balls;
    }

    private void validateUniqueBall(List<Integer> numbers) {
        if (numbers.stream()
                .distinct().count() != MAX_LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public String getBallsString() {
        return balls.toString();
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public List<Integer> getBallNumbers() {
        return balls.stream()
                .map(Ball::getNumber)
                .toList();
    }
}
