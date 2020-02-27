package lotto.domain;

import java.util.Set;

public class WinningLotto {

    private Lotto lottoNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(Set<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = new Lotto(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    Ranks match(Lottos lottos) {
        Ranks ranks = new Ranks();
        for (Lotto lotto : lottos.getLottos()) {
            int matchNumber = (int)lottoNumbers.getLottoNumbers().stream()
                .filter(lottoNumber -> lotto.getLottoNumbers().contains(lottoNumber))
                .count();
            addNullIfNoRank(ranks, matchNumber);
            ranks.add(Rank.valueOf(matchNumber, matchBonusNumber(bonusNumber)));
        }
        return ranks;
    }

    private void addNullIfNoRank(Ranks ranks, int matchNumber) {
        if (!Rank.isValid(matchNumber)) {
            ranks.add(null);
        }
    }

    private boolean matchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.getLottoNumbers().contains(bonusNumber);
    }
}
