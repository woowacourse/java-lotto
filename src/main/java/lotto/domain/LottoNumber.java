package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    public static final int MAX_NUMBER = 45;
    public static final int MIN_NUMBER = 1;
    public static final String NUMBER_RANGE_ERROR = "[ERROR] 1 ~ 45 사이의 숫자를 입력해 주세요.";
    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public LottoNumber(String input) {
        int number = validateNumber(input);
        validateRange(number);
        this.number = number;
    }

    private int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    private void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber lottoNumber = (LottoNumber) o;
        return Objects.equals(number, lottoNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
