package lotto.domain.lotto;

import java.util.List;

import static lotto.view.ErrorMessages.ERROR_BONUS_LOTTO_NUMBER_DUPLICATED;

public class WinningNumbers {
    private LottoLine lottoNumbers;
    private LottoNumber bonusNumber;

    public WinningNumbers(LottoLine lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.getValues().contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_LOTTO_NUMBER_DUPLICATED.getMessage());
        }
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }


    public LottoLine getLastWinningLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getLastWinBonusBall() {
        return bonusNumber;
    }
}
