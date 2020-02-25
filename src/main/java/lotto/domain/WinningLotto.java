package lotto.domain;

import java.util.Set;

public class WinningLotto {

    public static final String BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE = "보너스 숫자가 로또 숫자와 중복입니다.";
    private LottoTicket winningLottoNumber;
    private LottoNumber bonusNumber;

    public WinningLotto(Set<LottoNumber> lottoNumbers, int aBonusNumber) {
        this.winningLottoNumber = new LottoTicket(lottoNumbers);
        LottoNumber bonusNumber = new LottoNumber(aBonusNumber);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (this.winningLottoNumber.isContain(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }

}
