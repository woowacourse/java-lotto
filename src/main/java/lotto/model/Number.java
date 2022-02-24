package lotto.model;

import lotto.model.exception.InvalidNumberRangeException;
import java.util.Objects;

public class Number {

    private final int number;

    public Number(int number) {
        if (isInvalidRange(number)) {
            throw new InvalidNumberRangeException();
        }
        this.number = number;
    }

    private boolean isInvalidRange(int number) {
        return 1 > number || number > 45;
    }

    public int getIntValue() {
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
        Number that = (Number) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
