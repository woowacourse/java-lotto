package domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
    public static final List<Integer> LOTTO_NUMBERS;
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    private final int number;

    static final String ERROR_MESSAGE_FOR_OUT_OF_RANGE_NUMBER =
            String.format("%d에서 %d 사이의 값을 입력해주세요.", MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER);

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (isOutOfRange(number)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_OUT_OF_RANGE_NUMBER);
        }
    }

    private boolean isOutOfRange(int number) {
        return MAXIMUM_LOTTO_NUMBER < number || number < MINIMUM_LOTTO_NUMBER;
    }

    public int getNumber() {
        return number;
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
