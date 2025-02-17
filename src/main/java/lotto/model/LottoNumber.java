package lotto.model;

import java.util.Objects;

public class LottoNumber {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    private final int number;

    public LottoNumber(int number) {
        validateNumberInRange(number);
        this.number = number;
    }

    private void validateNumberInRange(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d부터 %d 사이의 수여야 합니다.",
                            MIN_VALUE, MAX_VALUE));
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final LottoNumber inputNumber = (LottoNumber) object;
        return number == inputNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
