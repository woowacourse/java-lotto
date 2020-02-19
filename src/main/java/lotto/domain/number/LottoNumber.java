package lotto.domain.number;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable {
    private static final Map<Integer, LottoNumber> lottoNumberMapper = new Hashtable<>();

    private int number;

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;

    static {
        for (int i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            lottoNumberMapper.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateBound(number);
        return lottoNumberMapper.get(number);
    }

    public int getNumber() {
        return number;
    }

    private static void validateBound(int number) {
        if (LOWER_BOUND > number || UPPER_BOUND < number) {
            throw new IllegalArgumentException("범위를 벗어난 숫자가 생성되었습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || getClass() != o.getClass()) {
            throw new IllegalStateException("null 혹은 타입이 맞지 않습니다.");
        }
        LottoNumber that = (LottoNumber) o;

        return Integer.compare(number, that.number);
    }

    public static Map<Integer, LottoNumber> getLottoNumberMapper() {
        return Collections.unmodifiableMap(lottoNumberMapper);
    }
}
