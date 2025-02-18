package lotto.domain;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(LottoNumber bonusNumber) {
        winningLotto.checkBonusDuplicate(bonusNumber);
    }

    public Prizes calculatePrize(Lottos lottos) {
        return lottos.calculatePrize(winningLotto, bonusNumber);
    }

}
