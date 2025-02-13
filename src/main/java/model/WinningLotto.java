package model;

import utils.Validator;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(String input, String bonusNumber) {
        lotto = new Lotto(input);

        Validator.validateNumeric(bonusNumber);
        int parsed = Integer.parseInt(bonusNumber);

        Validator.validateRange(parsed, 1, 45);

        validateLottoNumberDuplicate(parsed);
        this.bonusNumber = parsed;
    }

    private void validateLottoNumberDuplicate(int parsed) {
        if (lotto.isContained(parsed)) {
            throw new IllegalArgumentException();
        }
    }
}
