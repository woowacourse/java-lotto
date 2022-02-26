package model;

import validator.LottoNumberValidator;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        LottoNumberValidator.validate(number);
        this.number = number;
    }

    public int value() {
        return number;
    }
}
