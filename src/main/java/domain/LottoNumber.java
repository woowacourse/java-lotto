package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    private final int lottoNumber;

    static {
        for (int i = MIN_BOUND; i <= MAX_BOUND; i++) {
            CACHE.add(new LottoNumber(i));
        }
    }

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber valueOf(int number) {
        checkRange(number);
        return CACHE.get(number - 1);
    }

    private static void checkRange(int lottoNumber) {
        if (lottoNumber < MIN_BOUND || lottoNumber > MAX_BOUND) {
            throw new IllegalArgumentException("로또 범위를 벗어난 숫자입니다.");
        }
    }

    public static List<LottoNumber> lottoNumbers() {
        return Collections.unmodifiableList(CACHE);
    }

    public int getLottoNumber() {
        return lottoNumber;
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
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.lottoNumber, o.lottoNumber);
    }
}
