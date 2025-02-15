package lotto.model;

import static lotto.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MIN;

import lotto.Rank;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto == null) {
            throw new IllegalArgumentException("당첨 번호를 확인할 수 없습니다.");
        }
        if (bonusNumber < LOTTO_NUMBER_MIN || bonusNumber > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(
                    "보너스 번호는 %d부터 %d 사이의 수여야 합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank determineRank(Lotto lotto) {
        int matchCount = winningLotto.countMatchingNumbers(lotto);
        boolean isBonusMatch = lotto.contains(bonusNumber);
        return Rank.classifyRank(matchCount, isBonusMatch);
    }
}
