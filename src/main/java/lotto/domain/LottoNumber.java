package lotto.domain;

import lotto.constant.ErrorMessage;

public record LottoNumber(int number) {

    static final int MINIMUM_LOTTO_NUMBER = 1;
    static final int MAXIMUM_LOTTO_NUMBER = 45;

    public LottoNumber {
        validate(number);
    }

    private void validate(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_IS_UNVALID);
        }
    }
}
