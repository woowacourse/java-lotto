package lotto.domain.number;

import java.util.Objects;

public class PayOut {
    public static final PayOut ZERO = new PayOut();
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

    public int getValueAsInt() {
        return value.getValueAsInt();
    }

    public PayOut subtract(PayOut payOut) {
        Number subtractedNumber = value.subtract(payOut.value);

        if(subtractedNumber.equals(ZERO)) {
            return new PayOut();
        }

        return new PayOut(value.subtract(payOut.value));
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