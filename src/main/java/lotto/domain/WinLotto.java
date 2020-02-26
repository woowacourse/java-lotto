package lotto.domain;

import lotto.util.LottoUtils;

public class WinLotto {
    public static final String ERROR_MESSAGE_SIZE = "6개의 숫자가 아닙니다.";
    private final Lotto winLotto;
    private final BonusBall bonusBall;

    public WinLotto(String[] winLotto, String bonusBall) {
        validateSize(winLotto);
        this.winLotto = new Lotto(LottoUtils.toLottoNoSet(winLotto));
        this.bonusBall = new BonusBall(bonusBall);
    }

    private void validateSize(String[] winLotto) {
        if (winLotto.length != 6) {
            throw new IllegalArgumentException(ERROR_MESSAGE_SIZE);
        }
    }

    public int findHitCount(Lotto lotto) {
        return lotto.findHitCount(winLotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return bonusBall.contains(lotto);
    }
}
