package domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final List<LottoNumber> LOTTO_NUMBERS;
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;

    static final String ERROR_MESSAGE_FOR_OUT_OF_RANGE_NUMBER =
            String.format("%d에서 %d 사이의 값을 입력해주세요.", MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER);

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validateRange(number);

        int lottoNumberIndex = number - 1;
        return LOTTO_NUMBERS.get(lottoNumberIndex);
    }

    private static void validateRange(int number) {
        boolean isOutOfRange = MAXIMUM_LOTTO_NUMBER < number || number < MINIMUM_LOTTO_NUMBER;
        if (isOutOfRange) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_OUT_OF_RANGE_NUMBER);
        }
    }

    public int toInt() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber otherLottoNumber) {
        return this.number - otherLottoNumber.number;
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
