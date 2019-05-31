package lotto.domain;

import java.util.*;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int FIRST_INDEX_AFTER_SHUFFLED = 0;
    private static final int LAST_INDEX_AFTER_SHUFFLED = 6;

    private final int number;
    private static final List<LottoNumber> numbers;

    static {
        numbers = new ArrayList<>();
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(i -> numbers.add(new LottoNumber(i)));
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber get(int number) {
        for (LottoNumber lottoNumber : numbers) {
            if (lottoNumber.number == number) {
                return lottoNumber;
            }
        }
        throw new IllegalArgumentException("로또 번호는 1부터 45사이입니다.");
    }

    static Set<LottoNumber> getShuffledNumbers() {
        Collections.shuffle(numbers);
        return new TreeSet<>(numbers.subList(FIRST_INDEX_AFTER_SHUFFLED, LAST_INDEX_AFTER_SHUFFLED));
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
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
        return Integer.toString(number);
    }
}
