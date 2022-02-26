package lotto.receiver;

import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.receiver.validator.BonusNumberValidator;

public class BonusNumberReceiver {

    public static LottoNumber receive(String input, WinningLotto winningLotto) {
        LottoNumber bonusNumber = LottoNumber.findByNumber(Integer.parseInt(input));
        BonusNumberValidator.validate(bonusNumber, winningLotto);
        return bonusNumber;
    }
}
