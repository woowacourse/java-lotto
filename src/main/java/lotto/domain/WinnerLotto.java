package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;

import java.util.HashSet;
import java.util.List;

public class WinnerLotto {
    private final LottoNumbers winnerNumbers;
    private final LottoNumber bonusNumber;

    public WinnerLotto(LottoNumbers winnerNumbers, LottoNumber bonusNumber) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static void validateWinnerNumbers(List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != LOTTO_NUM_SIZE) {
            throw new IllegalArgumentException("중복은 불가능합니다.");
        }
    }

    public static void validateBonusNumbers(List<LottoNumber> winnerNumbers, LottoNumber bonusNumber) {
        if (winnerNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버가 당첨 번호에 중복됩니다.");
        }
    }

    public long getMatchCount(Lotto lotto) {
        return lotto.getMatchCount(winnerNumbers);
    }


    public boolean hasBonus(Lotto lotto) {
        return lotto.hasBonusNumber(bonusNumber);
    }
}
