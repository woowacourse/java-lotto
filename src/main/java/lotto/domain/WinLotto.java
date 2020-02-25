package lotto.domain;

import lotto.util.LottoUtils;

public class WinLotto {
    private final Lotto winLotto;
    private final BonusBall bonusBall;

    public WinLotto(String[] winLotto, String bonusBall) {
        this.winLotto = new Lotto(LottoUtils.toLottoNoList(winLotto));
        this.bonusBall = new BonusBall(bonusBall);
    }

    public int findHitCount(Lotto lotto) {
        return lotto.findHitCount(winLotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return bonusBall.contains(lotto);
    }
}
