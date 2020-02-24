package lotto.domain.lottoTicket;

import java.util.Collections;
import java.util.List;

public class WinningLotto {
    private static final String BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE = "보너스 번호가 당첨 번호와 같습니다.";

    private LottoNumber bonusNumber;
    private Lotto winningLotto;

    public WinningLotto(List<LottoNumber> numbers, int bonusNumber) {
        this.winningLotto = new Lotto(numbers);
        validateBonusNumberDuplication(bonusNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    void validateBonusNumberDuplication(int bonusNumber) {
        if (winningLotto.getLottoNumbers().contains(new LottoNumber(bonusNumber))) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }

    public boolean isBonusNumber(Lotto lotto) {
        return lotto.isContainBonusNumber(bonusNumber);
    }

    public List<LottoNumber> getWinningLotto() {
        return Collections.unmodifiableList(winningLotto.getLottoNumbers());
    }
}