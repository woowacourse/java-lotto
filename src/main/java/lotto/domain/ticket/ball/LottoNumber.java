package lotto.domain.ticket.ball;

import java.util.Objects;

public final class LottoNumber {
    private static final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "%d 로또 번호는 1부터 45까지 허용됩니다.";
    private final int number;

    private LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    public static LottoNumber from(int number) {
        return new LottoNumber(number);
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(String.format(LOTTO_NUMBER_EXCEPTION_MESSAGE, number));
        }
    }

    public boolean isEqualNumber(int number) {
        return this.number == number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
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
