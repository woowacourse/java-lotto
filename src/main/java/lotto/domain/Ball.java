package lotto.domain;

import lotto.exception.InvalidInputException;

import java.util.*;

public class Ball implements Comparable<Ball> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final Map<Integer, Ball> BALL_CACHE = new HashMap<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            BALL_CACHE.put(i, new Ball(i));
        }
    }

    private final int number;

    public Ball(int number) {
        validate(number);
        this.number = number;
    }

    public Ball(String number) {
        this(Integer.parseInt(checkType(number)));
    }

    private static String checkType(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("숫자만 입력하시기 바랍니다.");
        }
        return number;
    }

    private void validate(int number) {
        if (number < MIN_NUMBER || MAX_NUMBER < number) {
            throw new InvalidInputException(String.format("%d 이상 %d 이하의 숫자만 가능합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }

    public static List<Ball> getShuffledLottoNumbers() {
        List<Ball> balls = new ArrayList<>(BALL_CACHE.values());
        Collections.shuffle(balls);
        return Collections.unmodifiableList(balls);
    }

    public static Ball valueOf(int number) {
        return BALL_CACHE.get(number);
    }

    public static Ball valueOf(String number) {
        return BALL_CACHE.get(Integer.parseInt(number));
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
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
