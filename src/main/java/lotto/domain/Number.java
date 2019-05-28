package lotto.domain;

import lotto.NumberValidException;

public class Number {
    private int number;

    private Number(int number) {
        if (number >= 45 || number < 1) {
            throw new NumberValidException("로또번호에 해당되지 않는 숫자입니다.");
        }
        this.number = number;
    }

    public static Number of(int number) {
        return new Number(number);
    }
}
