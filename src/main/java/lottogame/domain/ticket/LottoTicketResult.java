package lottogame.domain.ticket;

public class LottoTicketResult {

    private final int matchCount;
    private final boolean isBonusMatch;

    public LottoTicketResult(final int matchCount, final boolean isBonusMatch) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }
}
