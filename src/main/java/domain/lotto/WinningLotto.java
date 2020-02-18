package domain.lotto;

public class WinningLotto {

    private LottoTicket winningLotto;
    private LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.has(bonusNumber)) {
            throw new IllegalArgumentException();
        }
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }
}
