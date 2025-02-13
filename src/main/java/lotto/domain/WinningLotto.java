package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstants;
import lotto.util.Parser;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, String bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = validate(bonusNumber);
    }

    private int validate(String bonusNumber) {
        int parsedBonusNumber = Parser.validateNumber(bonusNumber, ErrorMessage.BONUS_NUMBER_FORMAT_ERROR.getMessage());
        checkRange(parsedBonusNumber);
        validateBonusNumber(lotto, parsedBonusNumber);
        return parsedBonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        lotto.checkDuplicate(bonusNumber);
    }

    private void checkRange(int number) {
        if (number < LottoConstants.LOTTO_MINIMUM_NUMBER.getNumber() || number > LottoConstants.LOTTO_MAXIMUM_NUMBER.getNumber()) {
            throw new IllegalArgumentException(ErrorMessage.RANGE_ERROR.getMessage());
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

}
