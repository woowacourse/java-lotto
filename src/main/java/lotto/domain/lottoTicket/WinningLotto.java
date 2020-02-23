package lotto.domain.lottoTicket;

import java.util.List;

public class WinningLotto extends Lotto {
    private static final String BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE = "보너스 번호가 당첨 번호와 같습니다.";

    private LottoNumber bonusNumber;

    public WinningLotto(List<LottoNumber> numbers, int bonusNumber) {
        super(numbers);
        validateBonusNumberDuplication(bonusNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    void validateBonusNumberDuplication(int bonusNumber) {
        if (lottoNumbers.contains(new LottoNumber(bonusNumber))) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }

    public boolean isBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}