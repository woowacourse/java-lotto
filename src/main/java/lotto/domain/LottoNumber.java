package lotto.domain;

import lotto.Exception.InvalidLottoNumberException;

public class LottoNumber {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private final int number;

    public LottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException("0~45 사이의 로또숫자만 가능합니다.");
        }
        this.number = number;
    }

}
