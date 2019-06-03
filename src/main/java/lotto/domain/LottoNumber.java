package lotto.domain;

import lotto.exception.NumberValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    private LottoNumber(final int number) {
        if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
            throw new NumberValidException("로또번호에 해당되지 않는 숫자입니다.");
        }
        this.number = number;
    }

    static LottoNumber valueOf(int number) {
        return LottoNumbers.lottoNumbers.get(number - 1);
    }

    private static class LottoNumbers {
        private static List<LottoNumber> lottoNumbers = new ArrayList<>();

        static {
            IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                    .boxed()
                    .forEach(number -> lottoNumbers.add(new LottoNumber(number)));
        }

    }

    @Override
    public String toString() {
        return "" + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}


