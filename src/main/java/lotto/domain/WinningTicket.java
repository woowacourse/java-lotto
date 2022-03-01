package lotto.domain;

public class WinningTicket {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusBall;

    public WinningTicket(LottoTicket lottoTicket, LottoNumber bonusBall) {
        validate(lottoTicket, bonusBall);
        winningTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }

    private void validate(LottoTicket lottoTicket, LottoNumber bonusBall) {
        if (lottoTicket.isMatch(bonusBall)) {
            throw new IllegalArgumentException("보너스 숫자가 로또 번호에 포함되어 있습니다.");
        }
    }

    public LottoRank compare(LottoTicket lottoTicket) {
        int matchCount = lottoTicket.countMatch(winningTicket);
        boolean isBonusMatch = lottoTicket.isMatch(bonusBall);
        return LottoRank.find(matchCount, isBonusMatch);
    }
}
