package domain;

import static validator.NumberValidators.validateLottoNumberRange;

import java.util.HashMap;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final HashMap<Integer, LottoNumber> cache = new HashMap<>();

    private final int number;

    private LottoNumber(int value) {
        number = value;
    }

    public static LottoNumber of(int value) {
        LottoNumber lottoNumber = getCacheIfExists(value);

        if (lottoNumber != null) {
            return lottoNumber;
        }

        validateLottoNumberRange(value);
        return createNewCache(value);
    }

    private static LottoNumber getCacheIfExists(int num) {
        return cache.get(num);
    }

    private static LottoNumber createNewCache(int num) {
        LottoNumber lottoNumber = new LottoNumber(num);

        cache.put(num, lottoNumber);

        return lottoNumber;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.number - lottoNumber.getNumber();
    }

    @Override
    public String toString() {
        return "LottoNumber{" + "number=" + number + '}';
    }
}
