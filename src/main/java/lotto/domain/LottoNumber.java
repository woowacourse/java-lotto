package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String ERROR_NUMBER_RANGE = "로또 번호가 범위를 벗어났습니다.";
    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;

    private static final List<LottoNumber> lottoNumberCache = new ArrayList<>();

    static {
        for (int i = MINIMUM_RANGE - 1; i <= MAXIMUM_RANGE; i++) {
            lottoNumberCache.add(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        if (number < MINIMUM_RANGE || number > MAXIMUM_RANGE) {
            throw new RuntimeException(ERROR_NUMBER_RANGE);
        }
        return lottoNumberCache.get(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.number - lottoNumber.number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) object;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
