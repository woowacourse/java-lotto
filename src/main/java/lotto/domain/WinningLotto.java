package lotto.domain;

import java.util.Set;

public class WinningLotto extends Lotto {
    private LottoNumber bonusNumber;

    public WinningLotto(Set<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        super(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    Rank match(Lotto lotto) {
        int matchNumber = (int)lottoNumbers.stream()
            .filter(lottoNumber -> lotto.lottoNumbers.contains(lottoNumber))
            .count();
        if (!Rank.isValid(matchNumber)) {
            return null;
        }
        return Rank.valueOf(matchNumber, matchBonusNumber(bonusNumber));
    }

    private boolean matchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}
