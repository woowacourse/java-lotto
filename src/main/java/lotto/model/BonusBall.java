package lotto.model;

import lotto.exception.OverlapWinNumberException;

public class BonusBall {
    private final String CHECK_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE = "당첨번호와 중복되는 숫자가 있습니다.";
    private int bonusball;

    public BonusBall(WinNumber winNumber, int bonusNumber) {
        checkContainsWinNumber(winNumber, bonusNumber);
        bonusball = bonusNumber;
    }

    private void checkContainsWinNumber(WinNumber winNumber, int bonusNumber) {
        if (winNumber.contains(bonusNumber)) {
            throw new OverlapWinNumberException(CHECK_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public int getBonusNumber() {
        return bonusball;
    }
}
