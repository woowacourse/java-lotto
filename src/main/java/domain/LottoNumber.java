package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private static final String LOTTO_NUMBER_RANGE_ERROR = "[ERROR] 로또 번호는 1~45 사이 정수만 가능합니다.";

    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE = new LinkedHashMap<>();

    static {
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            LOTTO_NUMBER_CACHE.put(i, new LottoNumber(i));
        }
    }

    private final int lottoNumber;


    public LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int lottoNumber) {
        checkRange(lottoNumber);
        return LOTTO_NUMBER_CACHE.get(lottoNumber);

    }

    public static List<LottoNumber> valueOf() {
        return new ArrayList<>(LOTTO_NUMBER_CACHE.values());
    }

    private static void checkRange(int number) {

        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR);
        }

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


}
