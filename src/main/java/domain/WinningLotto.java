package domain;

import java.util.Objects;

public class WinningLotto {

    private static final String ERROR_MESSAGE_BONUS_NUMBER_DUPLICATE = "보너스 볼은 당첨번호와 중복될 수 없습니다.";
    private static final String ERROR_MESSAGE_NULL = "값이 null 입니다.";

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        Objects.requireNonNull(winningLotto, ERROR_MESSAGE_NULL);
        Objects.requireNonNull(bonusNumber, ERROR_MESSAGE_NULL);
        validateDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BONUS_NUMBER_DUPLICATE);
        }
    }

    public LottoReward calculateMatchResult(Lotto lotto) {
        final int matchCount = lotto.calculateMatchCount(winningLotto);
        final boolean isBonus = lotto.containsNumber(bonusNumber);

        return LottoReward.find(matchCount, isBonus);
    }
}
