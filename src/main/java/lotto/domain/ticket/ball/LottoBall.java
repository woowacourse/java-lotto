package lotto.domain.ticket.ball;

import java.util.Objects;

public final class LottoBall {
    private final int number;

    public LottoBall(int number) {
        validateNumber(number);
        this.number = number;
    }

    public static LottoBall of(int number) {
        return new LottoBall(number);
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("입력값: " + number + ": 범위 이외 숫자");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoBall lottoBall = (LottoBall) o;
        return number == lottoBall.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "LottoBall{" +
                "number=" + number +
                '}';
    }
}
