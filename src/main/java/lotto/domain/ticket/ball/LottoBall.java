package lotto.domain.ticket.ball;

import java.util.Objects;

public final class LottoBall {
    private static final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "%d 로또 번호는 1부터 45까지 허용됩니다.";
    private final int number;

    private LottoBall(int number) {
        validateNumber(number);
        this.number = number;
    }

    public static LottoBall from(int number) {
        return new LottoBall(number);
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(String.format(LOTTO_NUMBER_EXCEPTION_MESSAGE, number));
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
