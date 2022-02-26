package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    public static final String INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE = "1과 45 사이의 숫자를 입력해야 합니다.";

    private static final Map<Integer, LottoNumber> lottoNumberCache = new HashMap<>();

    static {
        IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1)
                .forEach(i -> lottoNumberCache.put(i, new LottoNumber(i)));
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateLottoNumberRange(number);
        return lottoNumberCache.get(number);
    }

    private static void validateLottoNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static List<LottoNumber> getAllLottoNumbers() {
        return new ArrayList<>(lottoNumberCache.values());
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.number - lottoNumber.getNumber();
    }
}
