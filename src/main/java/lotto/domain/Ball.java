package lotto.domain;

import java.util.Objects;

public class Ball {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    private static final String ERROR_LOTTO_NUMBER = "로또 숫자가 아닙니다";

    private final int number;

    public Ball(Integer number) {
        validateLottoNumber(number);
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    private void validateLottoNumber(final Integer number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            // TODO: 예외 처리 별도 객체 생성
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ball)) {
            return false;
        }
        Ball ball = (Ball)obj;
        return this.number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
