package lotto.domain;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final String NUMBER_RANGE_ERROR_MESSAGE = "로또 번호의 범위는 1 ~ 45 사이입니다.";
    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS = IntStream.rangeClosed(START_NUMBER, END_NUMBER)
            .boxed()
            .collect(toMap(identity(), LottoNumber::new));

    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber of(int lottoNumber) {
        if (!LOTTO_NUMBERS.containsKey(lottoNumber)) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR_MESSAGE);
        }

        return LOTTO_NUMBERS.get(lottoNumber);
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
