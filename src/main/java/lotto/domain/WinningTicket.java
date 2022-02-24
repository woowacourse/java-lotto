package lotto.domain;

public class WinningTicket {

    private LottoTicket winningTicket;
    private LottoNumber bonusBall;

    public WinningTicket(LottoTicket winningTicket, LottoNumber bonusBall) {
        validateBonusDistinct(winningTicket, bonusBall);
        this.winningTicket = winningTicket;
        this.bonusBall = bonusBall;
    }

    private void validateBonusDistinct(LottoTicket winningTicket, LottoNumber bonusBall) {
        if (winningTicket.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoRank compare(LottoTicket lottoTicket) {
        int matchCount = lottoTicket.countMatch(winningTicket);
        boolean isBonusMatch = lottoTicket.isMatch(bonusBall);
        return LottoRank.find(matchCount, isBonusMatch);
    }
}
