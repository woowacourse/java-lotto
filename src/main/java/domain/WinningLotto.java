package domain;

import exception.ExceptionMessage;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.isContain(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank calculateRank(Lotto targetLotto) {
        return Rank.getRank(targetLotto.calculateMatchCount(lotto), hasBonus(targetLotto));
    }

    private boolean hasBonus(Lotto targetLotto) {
        return targetLotto.isContain(bonusNumber);
    }
}
