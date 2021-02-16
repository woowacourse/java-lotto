package lotto.domain.number;

public class PayOut extends Number {

    public PayOut(String value) {
        super(value);
        validateNegative();
    }

    public PayOut(int value) {
        this(String.valueOf(value));
    }

    private void validateNegative() {
        if (this.value < 0) {
            throw new IllegalArgumentException("입력값이 음수 입니다.");
        }
    }
}
