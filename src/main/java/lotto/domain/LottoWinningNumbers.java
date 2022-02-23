package lotto.domain;

import lotto.utils.Validation;

public class LottoWinningNumbers {

    public LottoWinningNumbers(final String numbers) {
        Validation.checkInputLottoWinningNumbers(numbers);
    }
}
