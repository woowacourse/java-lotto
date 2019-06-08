package lotto.domain;

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
}