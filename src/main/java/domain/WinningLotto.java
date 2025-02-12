package domain;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    private WinningLotto(final Lotto lotto, final int bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(final Lotto lotto, final int bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public static WinningLotto of(final Lotto lotto, final int bonusNumber) {
        return new WinningLotto(lotto, bonusNumber);
    }
}
