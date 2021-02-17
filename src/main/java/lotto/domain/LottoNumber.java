package lotto.domain;

import lotto.utils.ValidateUtils;

public class LottoNumber {

    private final int number;

    public LottoNumber(final int number) {
        this.number = number;
    }

    LottoNumber(final String number) {
        this(ValidateUtils.parseInt(number));
    }

    public Integer getNumber() {
        return number;
    }
}
