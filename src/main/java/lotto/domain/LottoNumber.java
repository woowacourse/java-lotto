package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 45;
    private static final String SCOPE_ERROR_MESSAGE
            = "로또 숫자의 범위는 " + FIRST_NUMBER + "부터 " + LAST_NUMBER + " 사이입니다.";
    private final int number;

    public LottoNumber(int number) {
        if (isOutOfScope(number)) {
            throw new InvalidLottoNumberException(SCOPE_ERROR_MESSAGE);
        }
        this.number = number;
    }

    private boolean isOutOfScope(int number) {
        return number < FIRST_NUMBER || number > LAST_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
