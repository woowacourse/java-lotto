package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber implements Comparable {
    private static final int LOTTO_LOWER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    static {
        for (int i = LOTTO_LOWER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    private final int lottoNumber;

    private LottoNumber(final int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int lottoNumber) {
        checkValidNumber(lottoNumber);
        return new LottoNumber(lottoNumber);
    }

    private static void checkValidNumber(int lottoNumber) {
        if (lottoNumber < LOTTO_LOWER_BOUND || lottoNumber > LOTTO_UPPER_BOUND) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_LOWER_BOUND + "부터 " + LOTTO_UPPER_BOUND + "까지 가능합니다.");
        }
    }

    int getValue() {
        return this.lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.lottoNumber, ((LottoNumber) o).getValue());
    }

    @Override
    public String toString() {
        return Integer.toString(lottoNumber);
    }
}
