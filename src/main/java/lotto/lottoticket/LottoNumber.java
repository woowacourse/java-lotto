package lotto.lottoticket;

import java.util.Objects;

import static lotto.lottoticket.LottoTicketValidation.ERROR_MESSAGE_INVALID_INPUT;
import static lotto.lottoticket.ticketnumber.RandomNumbersGenerator.MAXIMUM_NUMBER;
import static lotto.lottoticket.ticketnumber.RandomNumbersGenerator.MINIMUM_NUMBER;

public class LottoNumber {
    public static final String ERROR_MESSAGE_INVALID_RANGE = String.format("숫자는 %d부터 %d 사이여야 합니다.", MINIMUM_NUMBER, MAXIMUM_NUMBER);

    private static final LottoNumber[] cache;
    static {
        cache = new LottoNumber[(MAXIMUM_NUMBER - MINIMUM_NUMBER) + 1];
        for (int i = 0; i < MAXIMUM_NUMBER; i++) {
            cache[i] = new LottoNumber(i + MINIMUM_NUMBER);
        }
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(String value) {
        try {
            return cache[makeNumber(value) - MINIMUM_NUMBER];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_RANGE);
        }
    }

    public static LottoNumber of(int number) {
        try {
            return cache[number - MINIMUM_NUMBER];
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
