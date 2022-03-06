package lotto.domain;

public class WinningTicket {

    private LottoLine winningTicket;
    private LottoNumber bonusBall;

    public WinningTicket(LottoLine winningTicket, LottoNumber bonusBall) {
        validateBonusDistinct(winningTicket, bonusBall);
        this.winningTicket = winningTicket;
        this.bonusBall = bonusBall;
    }

    private void validateBonusDistinct(LottoLine winningTicket, LottoNumber bonusBall) {
        if (winningTicket.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoRank compare(LottoLine lottoLine) {
        int matchCount = lottoLine.countMatch(winningTicket);
        boolean isBonusMatch = lottoLine.isMatch(bonusBall);
        return LottoRank.find(matchCount, isBonusMatch);
    }
}
