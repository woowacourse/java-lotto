package lotto.domain;

public class WinningTicket {

    private LottoTicket winningTicket;
    private LottoNumber bonusBall;

    public WinningTicket(LottoTicket lottoTicket, LottoNumber bonusBall) {
        winningTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }

    public LottoRank compare(LottoTicket lottoTicket) {
        int matchCount = lottoTicket.countMatch(winningTicket);
        boolean isBonusMatch = lottoTicket.isMatch(bonusBall);
        return LottoRank.find(matchCount, isBonusMatch);
    }
}
