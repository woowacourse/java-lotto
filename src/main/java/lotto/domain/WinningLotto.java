package lotto.domain;

import lotto.domain.rating.Rating;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(final Lotto lotto, final LottoNumber bonusBall) {
        validateBonusBallNonInLotto(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private void validateBonusBallNonInLotto(final Lotto lotto, final LottoNumber bonusBall) {
        if (lotto.containNumber(bonusBall)) {
            throw new IllegalArgumentException("중복된 보너스 볼 입력입니다.");
        }
    }

    public Rating scratch(Lotto lotto) {
        return Rating.of(matchedLottoNumberCount(lotto), containBonusBall(lotto));
    }

    private int matchedLottoNumberCount(final Lotto lotto) {
        return lotto.countCommonValue(this.lotto);
    }

    private boolean containBonusBall(final Lotto lotto) {
        return lotto.containNumber(bonusBall);
    }
}
