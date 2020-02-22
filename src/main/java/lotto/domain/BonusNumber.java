package lotto.domain;

import lotto.exceptions.LottoNumberRangeException;

public class BonusNumber {
    private static final int MAX_LOTTO_NUMBER_RANGE = 45;
    private static final int MIN_LOTTO_NUMBER_RANGE = 1;
    private static final String LOTTO_NUMBER_RANGE_MESSAGE = "1~45 범위의 숫자만 로또 번호가 될 수 있습니다.";

    private int bonusNumber;

    public BonusNumber(int bonusNumber) {
        if (isInvalidNumberRange(bonusNumber)) {
            throw new LottoNumberRangeException(LOTTO_NUMBER_RANGE_MESSAGE);
        }
        this.bonusNumber = bonusNumber;
    }

    private boolean isInvalidNumberRange(int number) {
        return number > MAX_LOTTO_NUMBER_RANGE || number < MIN_LOTTO_NUMBER_RANGE;
    }

    int getBonusNumber() {
        return bonusNumber;
    }
}
