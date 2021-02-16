package domain;

import java.util.Objects;

public class LottoNumber {
    private static final int MIN_NUMBER_RANGE = 1;
    private static final int MAX_NUMBER_RANGE = 45;
    private static final String INTEGER_REGULAR_EXPRESSION = "^-?[0-9]+$";

    private final int number;

    public LottoNumber(String input) {
        validateInputFormat(input);
        int number = Integer.parseInt(input);
        validateNumberRange(number);
        this.number = number;
    }

    private void validateInputFormat(String input) {
        if (input.matches(INTEGER_REGULAR_EXPRESSION)) {
            return;
        }
        throw new InvalidInputException();
    }

    private void validateNumberRange(int number) {
        if (number < MIN_NUMBER_RANGE || number > MAX_NUMBER_RANGE) {
            throw new InvalidNumberRangeException();
        }
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
