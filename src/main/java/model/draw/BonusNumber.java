package model.draw;

import model.common.LottoValidator;

public class BonusNumber {

    private final int number;

    public BonusNumber(int number) {
        LottoValidator.validateLottoNumberRange(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
