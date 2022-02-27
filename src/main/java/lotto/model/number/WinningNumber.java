package lotto.model.number;

import java.util.Objects;

public class WinningNumber {

    private final Number number;

    private WinningNumber(Number number) {
        this.number = number;
    }

    public static WinningNumber from(String input) {
        Number number = Number.from(input.trim());
        return new WinningNumber(number);
    }

    public boolean match(int number) {
        return this.number.getNumber() == number;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        WinningNumber winningNumber = (WinningNumber) object;
        return number == winningNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
