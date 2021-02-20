package lotto.domain.number;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final Map<String, LottoNumber> LOTTO_NUMBERS_POOL;

    private final int number;

    static {
        LOTTO_NUMBERS_POOL = IntStream.rangeClosed(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX)
            .boxed()
            .collect(Collectors.toMap(String::valueOf, LottoNumber::new));
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(String number) {
        validateRange(number);
        return LOTTO_NUMBERS_POOL.get(number);
    }

    private static void validateRange(String number) {
        if (!LOTTO_NUMBERS_POOL.containsKey(number)) {
            throw new IllegalArgumentException("불가능한 로또 번호입니다.");
        }
    }

    public static List<LottoNumber> getAllLottoNumbers() {
        return new ArrayList<>(LOTTO_NUMBERS_POOL.values());
    }

    public int unwrap() {
        return number;
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
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}