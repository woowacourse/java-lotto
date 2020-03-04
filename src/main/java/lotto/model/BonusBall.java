package lotto.model;

import lotto.exception.OverlapWinNumberException;

public class BonusBall {
    private final String CHECK_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE = "당첨번호와 중복되는 숫자가 있습니다.";
    private LottoNumber bonusball;

    public BonusBall(WinNumber winNumber, LottoNumber bonusNumber) {
        checkNullWinNumber(winNumber);
        checkContainsWinNumber(winNumber, bonusNumber);
        bonusball = bonusNumber;
    }

    private void checkNullWinNumber(WinNumber winNumber) {
        if (winNumber == null) {
            throw new NullPointerException("당첨 번호의 값이 null 입니다.");
        }
    }

    private void checkContainsWinNumber(WinNumber winNumber, LottoNumber bonusNumber) {
        if (winNumber.contains(bonusNumber)) {
            throw new OverlapWinNumberException(CHECK_CONTAIN_WIN_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public LottoNumber getBonusNumber() {
        return bonusball;
    }
}
