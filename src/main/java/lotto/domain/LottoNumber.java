package lotto.domain;

import java.util.regex.Pattern;

public class LottoNumber {
    private static final String IS_NUMBER = "\\d+";

    private final int number;

    public LottoNumber(int number) {
        if (number < 1 || 45 < number) {
            throw new RuntimeException();
        }
        this.number = number;
    }

    public LottoNumber(String numberValue) {
        if (!Pattern.matches(IS_NUMBER, numberValue)) {
            throw new RuntimeException();
        }

        number = Integer.parseInt(numberValue);
    }

    public int getNumber() {
        return number;
    }

}
