package domain;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * LottoNumber.java
 * 로또 번호 1자리를 뜻하는 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
public class LottoNumber {
    private static final int MIN_NUMBER_RANGE = 1;
    private static final int MAX_NUMBER_RANGE = 45;
    private static final String ERROR_INVALID_NUMBER_RANGE = "[ERROR] 로또 번호는 1~45 사이로 입력해주세요.";

    private final int number;

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(int number) {
        if (number < MIN_NUMBER_RANGE || number > MAX_NUMBER_RANGE) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER_RANGE);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(this.number);
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
