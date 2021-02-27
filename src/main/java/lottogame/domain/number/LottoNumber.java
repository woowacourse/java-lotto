package lottogame.domain.number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {

    private static final int START_LOTTO_NUMBER = 1;
    private static final int FINISH_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    static {
        for (int i = START_LOTTO_NUMBER; i <= FINISH_LOTTO_NUMBER; i++) {
            CACHE.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(final int number) {
        this.number = number;
        validateNumberRange(this.number);
    }

    private void validateNumberRange(final int value) {
        if (value < START_LOTTO_NUMBER || FINISH_LOTTO_NUMBER < value) {
            throw new IllegalArgumentException("유효한 로또 번호 범위는 "
                + START_LOTTO_NUMBER + "~" + FINISH_LOTTO_NUMBER + "입니다.");
        }
    }

    public static LottoNumber valueOf(final int number) {
        LottoNumber lottoNumber = CACHE.get(number);
        if (Objects.isNull(lottoNumber)) {
            lottoNumber = new LottoNumber(number);
        }
        return lottoNumber;
    }

    public static List<LottoNumber> numbers() {
        return new ArrayList<>(CACHE.values());
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
