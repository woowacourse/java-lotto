package lottogame.domain.ticket;

public class LottoTicketResult {

    private final int matchCount;
    private final boolean isBonusMatched;

    public LottoTicketResult(final int matchCount, final boolean isBonusMatched) {
        this.matchCount = matchCount;
        this.isBonusMatched = isBonusMatched;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatched() {
        return isBonusMatched;
    }
}
