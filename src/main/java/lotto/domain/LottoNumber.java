package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;

import java.util.*;

public class LottoNumber {
    static final int LOTTO_START_NUMBER = 0;
    static final int LOTTO_LAST_NUMBER = 45;
    private static final List<LottoNumber> numbers = new ArrayList<>();
    private final int number;

    static {
        for (int i = LOTTO_START_NUMBER; i < LOTTO_LAST_NUMBER; i++) {
            numbers.add(new LottoNumber(i + 1));
        }
    }

    private LottoNumber(final int number) {
        this.number = number;
    }

    static LottoNumber getLottoNumber(final int number) {
        if (number <= LOTTO_START_NUMBER || number > LOTTO_LAST_NUMBER) {
            throw new InvalidLottoNumberException(number + "는 유효한 범위가 아닙니다. 로또 번호는 1부터 45 사이여야합니다.");
        }

        return numbers.get(number - 1);
    }

    public static List<LottoNumber> getAllLottoNumbers() {
        return new ArrayList<>(numbers);
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
