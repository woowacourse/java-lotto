package model.draw;

import exception.ExceptionMessage;
import java.util.List;
import model.LottoConstants;

public class BonusNumber {

    private final int number;

    public BonusNumber(int number, WinningNumber winningNumber) {
        validate(number, winningNumber);
        this.number = number;
    }

    public boolean hasMatchingNumberIn(List<Integer> targetNumbers) {
        return targetNumbers.contains(number);
    }

    public int getNumber() {
        return number;
    }

    private void validate(int number, WinningNumber winningNumber) {
        validateBonusNumberRange(number);
        validateBonusNumberDuplication(winningNumber, number);
    }

    private void validateBonusNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_LOTTO_NUMBER_RANGE);
        }
    }

    private void validateBonusNumberDuplication(WinningNumber winningNumber, int number) {
        if (winningNumber.contains(number)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_BONUS_NUMBER);
        }
    }
}
