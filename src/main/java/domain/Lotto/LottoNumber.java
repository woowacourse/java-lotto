package domain.Lotto;

import utils.ExceptionMessage;

import java.util.*;


public class LottoNumber {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> CACHE;

    static {
        Map<Integer, LottoNumber> cacheMap = new HashMap<>();
        for (int number = MINIMUM_LOTTO_NUMBER; number <= MAXIMUM_LOTTO_NUMBER; number++) {
            cacheMap.put(number, new LottoNumber(number));
        }
        CACHE = Collections.unmodifiableMap(cacheMap);
    }

    private final int number;

    public LottoNumber(int number) {
        validateLottoNumberBound(number);
        this.number = number;
    }

    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }

    public static LottoNumber valueOf(int number) {
        LottoNumber lottoNumber = CACHE.get(number);

        if (lottoNumber == null) {
            return new LottoNumber(number);
        }
        return lottoNumber;
    }

    public static List<LottoNumber> values() {
        return new ArrayList<>(CACHE.values());
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "" + number;
    }
}