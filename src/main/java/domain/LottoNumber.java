package domain;

import static validator.LottoNumberValidators.validateLottoNumberRange;

public class LottoNumber {

    private final int number;

    private LottoNumber (int value) {
        number = value;
    }

    public static LottoNumber of(int value) {
        validateLottoNumberRange(value);
        return new LottoNumber(value);
    }

    public int getNumber() {
        return number;
    }
}
