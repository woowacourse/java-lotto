package lotto.domain.lotto;

import java.util.Objects;
import lotto.utils.NumericStringValidator;

public class LottoNumber {

    public static final int MINIMUM_VALUE = 1;
    public static final int MAXIMUM_VALUE = 45;

    private final int number;

    public LottoNumber(int number) {
        if (outOfRange(number)) {
            throw new IllegalArgumentException(
                    String.format("로또의 번호는 %d 이상 %d 이하의 정수여야 합니다.", MINIMUM_VALUE, MAXIMUM_VALUE));
        }
        this.number = number;
    }

    private boolean outOfRange(int number) {
        return number < MINIMUM_VALUE || MAXIMUM_VALUE < number;
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
            throw new IllegalArgumentException(
                    String.format("로또의 번호는 %d 이상 %d 이하의 정수여야 합니다.", MINIMUM_VALUE, MAXIMUM_VALUE));
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
