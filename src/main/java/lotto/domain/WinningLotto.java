package lotto.domain;

public class WinningLotto {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusBall;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusBall) {
        this.winningTicket = winningTicket;
        this.bonusBall = bonusBall;
    }

    public boolean hasBonus(LottoTicket lottoTicket) {
        return lottoTicket.hasNumber(bonusBall);
    }

    public LottoTicket getNumbers() {
        return winningTicket;
    }
}
