package domain;

import static validator.NumberValidators.validateLottoNumberRange;

import java.util.HashMap;
import java.util.Optional;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final HashMap<Integer, LottoNumber> cache = new HashMap<>();

    private final int number;

    private LottoNumber(int value) {
        number = value;
    }

    public static LottoNumber of(int value) {
        return Optional.ofNullable(getCacheIfExists(value))
                .orElseGet(() -> createNewCache(value));
    }

    private static LottoNumber getCacheIfExists(int num) {
        return cache.get(num);
    }

    private static LottoNumber createNewCache(int num) {
        validateLottoNumberRange(num);
        cache.put(num, new LottoNumber(num));
        return cache.get(num);
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
