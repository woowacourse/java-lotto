package lotto.domain;

import lotto.utils.StringUtils;

public class WinLotto {
    private final Lotto winLotto;
    private final BonusBall bonusBall;

    public WinLotto(String winLotto, String bonusBall) {
        this.winLotto = new Lotto(StringUtils.toLottoNoList(winLotto));
        this.bonusBall = new BonusBall(bonusBall);
    }

    public int compare(Lotto lotto) {
        return lotto.compare(winLotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return bonusBall.isContainBonusBall(lotto);
    }
}
