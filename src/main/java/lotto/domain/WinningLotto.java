package lotto.domain;

import java.util.Objects;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
        checkDuplicatedBonus();
    }

    private void checkDuplicatedBonus() {
        if (winningLotto.isContain(bonusNumber)) {
            throw new IllegalArgumentException("입렫된 보너스 번호가 지난 당첨 로또 번호와 중복되어있습니다.");
        }
    }

    public Rank getWinning(Lotto lotto) {
        return Rank.valueOf(winningLotto.calculateCountOfMatch(lotto), isMatchBonus(lotto));
    }

    private boolean isMatchBonus(Lotto lotto) {
        return lotto.isContain(bonusNumber);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningLotto, that.winningLotto) &&
                Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto, bonusNumber);
    }
}