package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.util.Parser;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, String bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = validate(bonusNumber);
    }

    private LottoNumber validate(String bonusNumber) {
        int parsedBonusNumber = Parser.validateNumber(bonusNumber, ErrorMessage.BONUS_NUMBER_FORMAT_ERROR.getMessage());
        LottoNumber validBonusNumber = new LottoNumber(parsedBonusNumber);
        validateBonusNumber(winningLotto, validBonusNumber);
        return validBonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, LottoNumber validBonusNumber) {
        lotto.checkBonusDuplicate(validBonusNumber);
    }

    public Prizes calculatePrize(Lottos lottos) {
        return lottos.calculatePrize(winningLotto, bonusNumber);
    }

}
