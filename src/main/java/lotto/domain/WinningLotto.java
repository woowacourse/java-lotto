package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public class WinningLotto {

    public static final String BONUS_BALL_IN_LOTTO_ERROR_MESSAGE = "중복된 보너스 볼 입력입니다.";
    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(final Lotto lotto, final LottoNumber bonusBall) {
        validateBonusBallNonInLotto(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private void validateBonusBallNonInLotto(final Lotto lotto, final LottoNumber bonusBall) {
        if (lotto.containNumber(bonusBall)) {
            throw new IllegalArgumentException(BONUS_BALL_IN_LOTTO_ERROR_MESSAGE);
        }
    }

    public int compareLottoNumber(final Lotto lotto) {
        return lotto.countCommonValue(this.lotto);
    }

    public boolean compareBonusBall(final Lotto lotto) {
        return lotto.containNumber(bonusBall);
    }
}
