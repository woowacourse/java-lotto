package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.util.Parser;

public class WinningLotto {

    private Lotto lotto;
    private int bonusNumber;

    public WinningLotto(Lotto lotto, String bonusNumber) {
        this.lotto = lotto;
        validate(bonusNumber);
    }

    private void validate(String bonusNumber) {
        int parsedBonusNumber = Parser.validateNumber(bonusNumber, ErrorMessage.BONUS_NUMBER_FORMAT_ERROR.getMessage());
        checkRange(parsedBonusNumber);
        validateBonusNumber(lotto, parsedBonusNumber);
        this.bonusNumber = parsedBonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        lotto.checkDuplicate(bonusNumber);
    }

    private void checkRange(int number) {
        if (number <= 0 || number > 45) {
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
