package lotto.receiver;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.receiver.validator.BonusNumberValidator;
import lotto.receiver.validator.LottoNumberValidator;

public class BonusNumberReceiver {

    public static LottoNumber receive(String input, Lotto winningNumbers) {
        LottoNumberValidator.validate(input);
        LottoNumber bonusNumber = LottoNumber.findByNumber(Integer.parseInt(input));
        BonusNumberValidator.validate(LottoNumber.findByNumber(Integer.parseInt(input)), winningNumbers);
        return bonusNumber;
    }
}
