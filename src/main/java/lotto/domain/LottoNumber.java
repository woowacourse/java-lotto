package lotto.domain;

import lotto.exception.LottoNumberException;

import java.util.*;

public class LottoNumber implements Comparable<LottoNumber> {
    private static Map<Integer, LottoNumber> numbers = new HashMap<>();
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            numbers.put(i, new LottoNumber(i));
        }

        numbers = Collections.unmodifiableMap(numbers);
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        LottoNumber lottoNumber = numbers.get(number);

        if (lottoNumber == null) {
            throw new LottoNumberException();
        }

        return lottoNumber;
    }

    public static List<LottoNumber> values() {
        return new ArrayList<>(numbers.values());

    }

    @Override
    public int compareTo(LottoNumber other) {
        return number - other.number;
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

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
