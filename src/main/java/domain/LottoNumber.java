package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    protected static final int NUMBER_MIN = 1;
    protected static final int NUMBER_MAX = 45;
    private final static Map<Integer, LottoNumber> NUMBERS;

    private final int value;

    static {
        NUMBERS = IntStream.rangeClosed(NUMBER_MIN, NUMBER_MAX)
                .boxed()
                .collect(Collectors.toMap(number -> number, LottoNumber::new));
    }

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber of(int value) {
        LottoNumber lottoNumber = NUMBERS.get(value);
        if (lottoNumber == null) {
            throw new IllegalArgumentException("로또 숫자 범위 외 숫자입니다.");
        }

        return lottoNumber;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.value - o.value;
    }
}
