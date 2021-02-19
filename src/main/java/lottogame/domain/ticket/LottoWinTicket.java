package lottogame.domain.ticket;

public class LottoWinTicket {

    private final int matchCount;
    private final boolean isBonusMatch;

    public LottoWinTicket(final int matchCount, final boolean isBonusMatch) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
    }

    public boolean isSameMatchCount(int matchCount) {
        return this.matchCount == matchCount;
    }

    public boolean isSameBonusMatch(boolean isBonusMatch) {
        return this.isBonusMatch == isBonusMatch;
    }
}
