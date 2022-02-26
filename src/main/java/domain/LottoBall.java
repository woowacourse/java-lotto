package domain;

import java.util.Objects;

public class LottoBall {

    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final String ERROR_LOTTO_BALL_OUT_OR_RANGE = "로또 개별 숫자는 1부터 45까지 가능합니다.";

    private final int number;

    public LottoBall(int number) {
        if (number < LOTTO_MIN || number > LOTTO_MAX) {
            throw new IllegalArgumentException(ERROR_LOTTO_BALL_OUT_OR_RANGE);
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoBall that = (LottoBall) o;
        return getNumber() == that.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
    }

}
