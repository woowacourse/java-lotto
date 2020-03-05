package domain;

import java.util.*;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final Map<Integer, LottoNumber> allLottoNumbers = new HashMap<>();

    static {
        IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(index -> allLottoNumbers.put(index, new LottoNumber(index)));
    }

    private int number;

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber newLottoNumber(final int number) {
        checkLottoRange(number);
        return allLottoNumbers.get(number);
    }

    private static void checkLottoRange(final int number) {
        if (isNotLottoNumber(number)) {
            throw new IllegalArgumentException("로또 숫자 범위를 넘어섰습니다.");
        }
    }

    private static boolean isNotLottoNumber(final int number) {
        return number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER;
    }

    public static List<Integer> getAllLottoNumbers() {
        return new ArrayList<>(allLottoNumbers.keySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.number - o.number;
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
