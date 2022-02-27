package domain;

import static validator.NumberValidators.validateLottoNumberRange;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    public static final List<LottoNumber> LOTTO_NUMBERS_LIST = IntStream.range(MINIMUM_NUMBER, MAXIMUM_NUMBER)
            .boxed()
            .map(LottoNumber::of)
            .collect(Collectors.toList());

    private final int number;

    private LottoNumber(int value) {
        number = value;
    }

    public static LottoNumber of(int value) {
        return Optional.ofNullable(LottoNumberCache.getCacheIfExists(value))
                .orElseGet(() -> LottoNumberCache.createNewCache(value));
    }

    private static class LottoNumberCache {

        static final HashMap<Integer, LottoNumber> cache = new HashMap<>();

        static LottoNumber getCacheIfExists(int num) {
            return cache.get(num);
        }

        static LottoNumber createNewCache(int num) {
            validateLottoNumberRange(num);
            cache.put(num, new LottoNumber(num));
            return cache.get(num);
        }
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
