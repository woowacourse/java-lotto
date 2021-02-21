package lotto.domain.number;

import java.util.Objects;

public class PayOut {
    public static final PayOut ZERO = new PayOut();
    private static final int GAME_PRICE = 1000;
    private final Number value;

    public PayOut(Number number) {
        validateNegative(number.getValueAsInt());
        this.value = number;
    }

    public PayOut(int number) {
        this(new Number(number));
    }

    public PayOut(String number) {
        this(new Number(number));
    }

    private PayOut() {
        value = new Number(0);
    }

    private void validateNegative(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("입력값이 양수가 아닙니다.");
        }
    }

    public int getGameCount() {
        return value.getValueAsInt() / GAME_PRICE;
    }

    public int getValueAsInt() {
        return value.getValueAsInt();
    }

    public PayOut subtraction(PayOut payOut) {
        Number subtractedNumber = value.subtraction(payOut.value);

        if (subtractedNumber.equals(ZERO)) {
            return new PayOut();
        }

        return new PayOut(value.subtraction(payOut.value));
    }

    public PayOut subtractionUsingGameCount(int count) {
        return subtraction(new PayOut(count * GAME_PRICE));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayOut payOut = (PayOut) o;
        return Objects.equals(value, payOut.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}