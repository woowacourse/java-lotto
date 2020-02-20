package lotto.domain;

import lotto.utils.StringUtils;

public class WinLotto extends Lotto {
    private final BonusBall bonusBall;

    public WinLotto(String winLotto, String bonusBall) {
        super(StringUtils.toLottoNoList(winLotto));
        this.bonusBall = new BonusBall(bonusBall);
    }

    public int compare(Lotto lotto) {
        return (int) this.lottoNumbers.stream()
                .filter(x -> lotto.isContain(x))
                .count();
    }

    public boolean isMatchBonus(Lotto lotto) {
        return bonusBall.isContainBonusBall(lotto);
    }
}
