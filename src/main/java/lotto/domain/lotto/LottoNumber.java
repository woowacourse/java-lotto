package lotto.domain.lotto;

import java.util.Objects;
import lotto.utils.NumericStringValidator;

public class LottoNumber {

    public static final int MINIMUM_VALUE = 1;
    public static final int MAXIMUM_VALUE = 45;

    private final int number;

    public LottoNumber(int number) {
        if (number < MINIMUM_VALUE || MAXIMUM_VALUE < number) {
            throw new IllegalArgumentException("로또의 번호는 1이상 45이하의 정수여야 합니다.");
        }
        this.number = number;
    }

    private LottoNumber(String numberValue) {
        this(Integer.parseInt(numberValue));
    }

    public static LottoNumber valueOf(String numberValue) {
        numberValue = numberValue.trim();
        validateNumeric(numberValue);
        return new LottoNumber(numberValue);
    }

    private static void validateNumeric(String input) {
        if (!NumericStringValidator.isValid(input)) {
            throw new NumberFormatException("로또의 번호는 1이상 45이하의 정수여야 합니다.");
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
