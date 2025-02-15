package lotto.domain;

import lotto.util.NumberGenerator;

import java.util.Objects;

import static lotto.constant.ErrorMessage.OUT_OF_RANGE_LOTTO_NUMBER;

public record LottoNumber(
    int number
) implements Comparable<LottoNumber> {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;

    public LottoNumber {
        validate(number);
    }

    private void validate(int number) {
        if (number < MINIMUM || number > MAXIMUM) {
            throw new IllegalArgumentException(OUT_OF_RANGE_LOTTO_NUMBER.getMessage());
        }
    }

    public static LottoNumber random(NumberGenerator generator) {
        return new LottoNumber(generator.generate(MINIMUM, MAXIMUM));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber)o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }
}
