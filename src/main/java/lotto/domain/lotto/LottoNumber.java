package lotto.domain.lotto;

import lotto.exception.IllegalLottoNumberException;

import java.util.Objects;
import java.util.regex.Pattern;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    private final int value;

    public LottoNumber(String number) {
        validateLottoNumber(number);
        this.value = Integer.parseInt(number);
    }

    private void validateLottoNumber(String number) {
        if (isBlank(number) || isInvalidNumberFormat(number) || isInvalidLottoNumberRange(number)) {
            throw new IllegalLottoNumberException();
        }
    }

    private boolean isBlank(String number) {
        return "".equals(number);
    }

    private boolean isInvalidNumberFormat(String number) {
        return !NUMBER_PATTERN.matcher(number).matches();
    }


    private boolean isInvalidLottoNumberRange(String number) {
        return Integer.parseInt(number) < MIN_LOTTO_NUMBER ||
                Integer.parseInt(number) > MAX_LOTTO_NUMBER;
    }

    private int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(LottoNumber anotherLottoNumber) {
        return Integer.compare(this.getValue(), anotherLottoNumber.getValue());
    }
}
