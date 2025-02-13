package lotto.model;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(final Lotto winningLotto, final int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank calculateWinning(final Lotto lotto) {
        int matchingCount = lotto.calculateMatchingCount(winningLotto);
        return Rank.findBy(matchingCount, lotto.has(bonusNumber));
    }

    private void validate(final Lotto lotto, final int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplication(lotto, bonusNumber);
    }

    private void validateRange(final int bonusNumber) {
        if (bonusNumber < Lotto.MIN_LOTTO_NUMBER || bonusNumber > Lotto.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또는 1 이상 45 이하만 가능합니다.");
        }
    }

    private void validateDuplication(final Lotto lotto, final int bonusNumber) {
        if (lotto.has(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

}
