package lotto.domain;

import lotto.validator.BonusNumberValidator;

public class BonusNumber {

    private final LottoNumber bonusNumber;

    private BonusNumber(LottoNumber bonusNumber, WinningLotto winningLotto) {
        BonusNumberValidator.validate(bonusNumber, winningLotto);
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumber generateBonusNumberByConsole(String consoleInput, WinningLotto winningLotto) {
        return new BonusNumber(LottoNumber.findByNumber(Integer.parseInt(consoleInput)), winningLotto);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
