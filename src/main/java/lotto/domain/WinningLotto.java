package lotto.domain;

import java.util.Set;

public class WinningLotto {

    private Lotto lottoNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(Set<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = new Lotto(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    Rank match(Lotto lotto) {
        int matchNumber = (int)lottoNumbers.getLottoNumbers().stream()
            .filter(lottoNumber -> lotto.getLottoNumbers().contains(lottoNumber))
            .count();
        if (!Rank.isValid(matchNumber)) {
            return null;
        }
        return Rank.valueOf(matchNumber, matchBonusNumber(bonusNumber));
    }

    private boolean matchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.getLottoNumbers().contains(bonusNumber);
    }
}
