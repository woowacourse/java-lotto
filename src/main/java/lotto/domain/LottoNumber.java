package lotto.domain;

import static lotto.constant.ErrorMessage.OUT_OF_RANGE_LOTTO_NUMBER;

import lotto.util.NumberGenerator;

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
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }
}
