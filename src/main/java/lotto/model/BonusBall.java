package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.OverlapWinNumberException;

public class BonusBall {
    private static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자가 아닙니다.";
    private static final String IS_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE = "당첨번호와 중복되는 숫자가 있습니다.";
    private static int bonusball;

    public BonusBall(String input) {
        isNumber(input);
        int bonusNumber = Integer.parseInt(input);
        isContainsWinNumber(bonusNumber);
        bonusball = bonusNumber;
    }

    private void isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private void isContainsWinNumber(int bonusNumber) {
        if (WinNumber.contains(bonusNumber)) {
            throw new OverlapWinNumberException(IS_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public static int getBonusNumber() {
        return bonusball;
    }
}
