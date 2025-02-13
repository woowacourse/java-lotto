package domain;

import exception.LottoException;
import utility.StringUtility;

public class BonusNumber {
    private static final String INVALID_BONUS_NUMBER = "유효하지 않은 보너스 번호입니다.";
    private final LottoNumber lottoNumber;

    public BonusNumber(String bonusNumber) {
        validateBonusNumber(bonusNumber);
        lottoNumber = new LottoNumber(Integer.parseInt(bonusNumber));
    }

    private void validateBonusNumber(String bonusNumber) {
        validateIsEmpty(bonusNumber);
        validateIsNumber(bonusNumber);
    }

    private void validateIsEmpty(String bonusNumber) {
        if(bonusNumber == null){
            throw new LottoException(INVALID_BONUS_NUMBER);
        }
    }
    private void validateIsNumber(String bonusNumber) {
        if(StringUtility.isNumber(bonusNumber)){
            throw new LottoException(INVALID_BONUS_NUMBER);
        }
    }
}
