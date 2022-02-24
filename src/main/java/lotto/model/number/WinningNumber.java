package lotto.model.number;

import java.util.Objects;

public class WinningNumber implements LottoNumber {
    private static final String ERROR_TYPE = "[ERROR] 당첨 번호는 숫자로만 입력해주세요";

    private final int number;

    private WinningNumber(int number) {
        checkBound(number);
        this.number = number;
    }

    public static WinningNumber from(String input) {
        try {
            return new WinningNumber(Integer.parseInt(input.trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public boolean match(int number) {
        return this.number == number;
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
