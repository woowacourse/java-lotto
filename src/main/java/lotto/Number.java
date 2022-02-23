package lotto;

import java.util.Objects;

public class Number {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    private final int number;

    public Number(int number) {
        validateRangeOfNumber(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Number)) {
            return false;
        }
        Number number1 = (Number)o;
        return number == number1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    private void validateRangeOfNumber(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1이상 45이하이어야 한다.");
        }
    }
}
