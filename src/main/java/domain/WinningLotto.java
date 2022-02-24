package domain;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public int calculateMatchCount(Lotto otherLotto) {
        return winningLotto.calculateSameNumber(otherLotto);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
