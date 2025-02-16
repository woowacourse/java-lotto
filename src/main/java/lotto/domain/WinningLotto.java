package lotto.domain;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto lotto, final LottoNumber bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.winningLotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public WinningLotto(final Lotto lotto, final int bonusNumber) {
        this(lotto, new LottoNumber(bonusNumber));
    }

    private static void validateBonusNumber(final Lotto lotto, final LottoNumber bonusNumber) {
        if (lotto.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    public Rank calculateWinning(final Lotto lotto) {
        return Rank.of(lotto.findMatchCount(winningLotto), lotto.containsNumber(bonusNumber));
    }
}
