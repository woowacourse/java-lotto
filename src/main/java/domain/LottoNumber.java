package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final String ERROR_MESSAGE_FOR_OUT_OF_RANGE_NUMBER =
            String.format("%d에서 %d사이의 값을 입력해주세요", MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER);
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    private final int number;

    private LottoNumber(int lottoNumber) {
        this.number = lottoNumber;
    }

    public static LottoNumber getInstance(int lottoNumber) {
        validateRange(lottoNumber);

        if (CACHE.containsKey(lottoNumber)) {
            return CACHE.get(lottoNumber);
        }

        LottoNumber newInstance = new LottoNumber(lottoNumber);
        CACHE.put(lottoNumber, newInstance);
        return newInstance;
    }

    private static void validateRange(int number) {
        if (isOutOfRange(number)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_OUT_OF_RANGE_NUMBER);
        }
    }

    private static boolean isOutOfRange(int number) {
        return number < MINIMUM_LOTTO_NUMBER || MAXIMUM_LOTTO_NUMBER < number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber otherLottoNumber) {
        return Integer.compare(number, otherLottoNumber.number);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) object;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
    }
}
