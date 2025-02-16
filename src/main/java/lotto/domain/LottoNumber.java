package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {

    static final int MINIMUM_LOTTO_NUMBER = 1;
    static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final List<LottoNumber> CACHE;

    static {
        CACHE = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .toList();
    }

    private final int number;

    private LottoNumber(final int number) {
        validate(number);
        this.number = number;
    }

    public static LottoNumber from(final int number) {
        validate(number);
        return CACHE.get(number - 1);
    }

    private static void validate(final int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1과 45 사이여야 합니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final LottoNumber that)) {
            return false;
        }
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    public int getNumber() {
        return number;
    }
}
