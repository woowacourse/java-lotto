package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNo bonusNo;

    public WinningLotto(final Lotto lotto, final LottoNo bonusNo) {
        validateDuplication(lotto, bonusNo);
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    private void validateDuplication(final Lotto lotto, final LottoNo bonusNo) {
        if (lotto.isMatch(bonusNo)) {
            throw new IllegalArgumentException("보너스 번호와 당첨 번호 중복은 안됩니다.");
        }
    }

    public WinPrize generateWinPrize(List<Lotto> userLottos){
        WinPrize winPrize = new WinPrize();
        for (final Lotto userLotto : userLottos) {
            winPrize.addWinCount(this.match(userLotto));
        }
        return winPrize;
    }

    public Rank match(final Lotto userLotto) {
        int countOfMatch = userLotto.countOfMatch(this.lotto);
        boolean matchBonusNo = userLotto.isMatch(bonusNo);

        return Rank.valueOf(countOfMatch, matchBonusNo);
    }

    public String getLottos() {
        return lotto.toString();
    }

    public int getBonusNo() {
        return bonusNo.value();
    }

    @Override
    public String toString() {
        return lotto.toString() + " bonus = " + bonusNo;
    }
}
