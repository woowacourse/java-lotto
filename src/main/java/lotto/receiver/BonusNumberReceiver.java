package lotto.receiver;

import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.receiver.validator.BonusNumberValidator;
import lotto.receiver.validator.LottoNumberValidator;

public class BonusNumberReceiver {

    public static LottoNumber receive(String input, WinningLotto winningNumbers) {
        LottoNumberValidator.validate(input);
        LottoNumber bonusNumber = LottoNumber.findByNumber(Integer.parseInt(input));
        BonusNumberValidator.validate(bonusNumber, winningNumbers);
        return bonusNumber;
    }
}
