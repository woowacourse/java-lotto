package lotto.domain;

public class WinningLotto {
    Lotto winningLotto;
    LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validWinningLotto(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.match(bonusNumber)) {
            throw new InvalidWinningLottoException("로또와 보너스 넘버는 중복될 수 없습니다.");
        }
    }
}
