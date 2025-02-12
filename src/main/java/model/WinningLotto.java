package model;

import utils.Validator;

public class WinningLotto {

    private Lotto lotto;
    private int bonusNumber;

    public WinningLotto(String input, String bonusNumber) {
        lotto = new Lotto(input);

        Validator.validateNumeric(bonusNumber);
        int parsed = Integer.parseInt(bonusNumber);

        Validator.validateRange(parsed, 45, 1);
        this.bonusNumber = parsed;
    }
}
