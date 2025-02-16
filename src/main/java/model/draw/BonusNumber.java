package model.draw;

import exception.ExceptionMessage;
import model.common.LottoValidator;

public class BonusNumber {

    private final int number;

    public BonusNumber(int number, WinningNumber winningNumber) {
        validate(number, winningNumber);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void validate(int number, WinningNumber winningNumber) {
        LottoValidator.validateLottoNumberRange(number);
        validateBonusNumberDuplication(winningNumber, number);
    }

    private void validateBonusNumberDuplication(WinningNumber winningNumber, int number) {
        if (winningNumber.contains(number)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_BONUS_NUMBER);
        }
    }
}
