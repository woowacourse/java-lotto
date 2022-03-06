package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import util.Validator;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private static final int CACHE_INDEX_ADJUSTER = -1;
    private static final String LOTTO_NUMBER_OUT_OF_RANGE_EXCEPTION
            = "[ERROR] 로또 번호는 1이상 45이하여야 합니다.";
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    static {
        for (int number = MIN_VALUE; number <= MAX_VALUE; ++number) {
            CACHE.add(new LottoNumber(number));
        }
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        validate(number);
        LottoNumber lottoNumber = CACHE.get(number + CACHE_INDEX_ADJUSTER);

        if (Objects.isNull(lottoNumber)) {
            lottoNumber = new LottoNumber(number);
        }
        return lottoNumber;
    }
    
    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber other) {
        Validator.checkArgumentIsNull(other);
        return this.number - other.number;
    }

    private static void validate(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE_EXCEPTION);
        }
    }
}
