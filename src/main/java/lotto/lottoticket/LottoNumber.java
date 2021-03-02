package lotto.lottoticket;

import java.util.Objects;
import java.util.stream.IntStream;

import static lotto.lottoticket.LottoTicketValidation.ERROR_MESSAGE_INVALID_INPUT;
import static lotto.lottoticket.ticketnumber.RandomNumbersGenerator.MAXIMUM_NUMBER;
import static lotto.lottoticket.ticketnumber.RandomNumbersGenerator.MINIMUM_NUMBER;

public class LottoNumber {
    public static final String ERROR_MESSAGE_INVALID_RANGE = String.format("숫자는 %d부터 %d 사이여야 합니다.", MINIMUM_NUMBER, MAXIMUM_NUMBER);

    private static final LottoNumber[] CACHE = new LottoNumber[(MAXIMUM_NUMBER - MINIMUM_NUMBER) + 1];

    static {
        IntStream.range(MINIMUM_NUMBER - 1, MAXIMUM_NUMBER)
                .forEach(index -> CACHE[index] = new LottoNumber(index + 1));
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(String value) {
        try {
            return CACHE[makeNumber(value) - MINIMUM_NUMBER];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_RANGE);
        }
    }

    public static LottoNumber of(int number) {
        try {
            return CACHE[number - MINIMUM_NUMBER];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_RANGE);
        }
    }

    public static int makeNumber(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
