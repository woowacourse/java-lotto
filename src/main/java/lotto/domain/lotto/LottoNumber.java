package lotto.domain.lotto;

import java.util.Objects;
import java.util.regex.Pattern;

public class LottoNumber {

    public static final int MINIMUM_VALUE = 1;
    public static final int MAXIMUM_VALUE = 45;
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    private final int number;

    public LottoNumber(int number) {
        if (number < MINIMUM_VALUE || MAXIMUM_VALUE < number) {
            throw new RuntimeException();
        }
        this.number = number;
    }

    public LottoNumber(String numberValue) {
        this(convertToLottoNumber(numberValue));
    }

    private static int convertToLottoNumber(String numberValue) {
        numberValue = numberValue.trim();
        validateNumeric(numberValue);
        return Integer.parseInt(numberValue);
    }

    public static void validateNumeric(String input) {
        if (!NUMERIC_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }

    public int toInt() {
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
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
