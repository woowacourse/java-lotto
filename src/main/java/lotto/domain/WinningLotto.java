package lotto.domain;

public class WinningLotto {
    Lotto winningLotto;
    int bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }
}
