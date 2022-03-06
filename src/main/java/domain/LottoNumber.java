package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumber {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;
    private static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = String.format("로또 번호는 %d~%d 사이로 입력해주세요.",
            MIN_VALUE, MAX_VALUE);

    private static final Map<Integer, LottoNumber> lottoNumberCache = new HashMap<>() {{
        IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
                .boxed()
                .map(LottoNumber::new)
                .forEach(lottoNumber -> put(lottoNumber.getValue(), lottoNumber));
    }};

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(int number) {
        validate(number);
        return lottoNumberCache.get(number);
    }

    public static void validate(int number) {
        if (isNotInRangeNumber(number)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private static boolean isNotInRangeNumber(int number) {
        return !(number <= MAX_VALUE && number >= MIN_VALUE);
    }

    public int getValue() {
        return value;
    }
}
