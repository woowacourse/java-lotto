package lotto.domain;

import lotto.exception.InvalidInputException;

import java.util.*;

public class Ball implements Comparable<Ball> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final Map<Integer, Ball> balls = new HashMap<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            balls.put(i, new Ball(i));
        }
    }

    private final int number;

    public Ball(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < MIN_NUMBER || MAX_NUMBER < number) {
            throw new InvalidInputException(String.format("%d 이상 %d 이하의 숫자만 가능합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }

    public static Ball valueOf(int number) {
        return new Ball(number);
    }

    public static Ball valueOf(String number) {
        return new Ball(Integer.parseInt(number));
    }

    public static List<Ball> generateAllBalls() {
        List<Ball> result = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            result.add(new Ball(i));
        }
        return result;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Ball ball) {
        return Integer.compare(this.number, ball.getNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
