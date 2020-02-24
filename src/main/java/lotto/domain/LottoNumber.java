package lotto.domain;

import java.util.Objects;

import static lotto.domain.NumberValidator.validateIfEmptyValueThenInvokeException;
import static lotto.domain.NumberValidator.validateIfNotNumberThenInvokeException;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber of(String value) {
        validate(value);
        return new LottoNumber(Integer.parseInt(value));
    }

    public static LottoNumber of(int value) {
        return new LottoNumber(value);
    }

    private static void validate(String number) {
        validateIfEmptyValueThenInvokeException(number, "번호를 입력하지 않으셨습니다.");
        validateIfNotNumberThenInvokeException(number, "숫자만 입력하시기 바랍니다.");
        validateIfNotInRangeThenInvokeException(number, String.format("%d 이상 %d 이하의 숫자만 입력 가능합니다.", MIN_VALUE,
                                                                      MAX_VALUE));
    }

    private static void validateIfNotInRangeThenInvokeException(String number, String message) {
        int num = Integer.parseInt(number);
        if (num < MIN_VALUE || MAX_VALUE < num) {
            throw new RuntimeException(message);
        }
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.value - o.value;
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

    public int get() {
        return value;
    }
}