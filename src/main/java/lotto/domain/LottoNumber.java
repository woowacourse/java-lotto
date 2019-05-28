package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {
    private static final Map<Integer, LottoNumber> numbers = new HashMap<>();
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_LAST_NUMBER = 45;
    private final int number;

    static {
        for (int i = LOTTO_START_NUMBER; i <= LOTTO_LAST_NUMBER; i++) {
            numbers.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(final int number) {
        if (number < LOTTO_START_NUMBER || number > LOTTO_LAST_NUMBER) {
            throw new InvalidLottoNumberException(number + "는 유효한 범위가 아닙니다. 로또 번호는 1부터 45 사이여야합니다.");
        }
        this.number = number;
    }

    public static LottoNumber of(final int number) {
        return new LottoNumber(number);
    }

    static LottoNumber getLottoNumber(final int number) {
        LottoNumber lottoNumber = numbers.get(number);
        if (lottoNumber == null) {
            throw new InvalidLottoNumberException(number + "는 유효한 범위가 아닙니다. 로또 번호는 1부터 45 사이여야합니다.");
        }
        return lottoNumber;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
