package domain;

import exception.LottoException;
import utility.StringUtility;

public class BonusNumber {

    private static final String INVALID_BONUS_NUMBER = "유효하지 않은 보너스 번호입니다.";
    private final LottoNumber lottoNumber;

    public BonusNumber(int inputBonusNumber) {
        validateBonusNumber(inputBonusNumber);
        lottoNumber = new LottoNumber(inputBonusNumber);
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return this.lottoNumber.equals(lottoNumber);
    }

    public boolean isDuplicate(WinningNumber winningNumber) {
        return winningNumber.isContain(lottoNumber);
    }

    private void validateBonusNumber(int bonusNumber) {
        validateZero(bonusNumber);
    }

    private void validateZero(int bonusNumber) {
        if(bonusNumber == 0){
            throw new LottoException(INVALID_BONUS_NUMBER);
        }
    }

}
