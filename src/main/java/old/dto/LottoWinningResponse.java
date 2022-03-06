package old.dto;

import java.util.Map;
import java.util.Objects;

import old.domain.LottoRank;

public class LottoWinningResponse implements Comparable<LottoWinningResponse> {

    private static final int SECOND_PLACE_COUNT = 5;

    private final int matchCount;
    private final int prize;
    private final boolean isBonusWin;
    private final int ticketCount;

    public LottoWinningResponse(int matchCount, int prize, boolean isBonusWin, int ticketCount) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.isBonusWin = isBonusWin;
        this.ticketCount = ticketCount;
    }

    public static LottoWinningResponse from(Map.Entry<LottoRank, Integer> rankCountEntry) {
        LottoRank rank = rankCountEntry.getKey();
        int frequency = rankCountEntry.getValue();
        return new LottoWinningResponse(
            rank.getMatchCount(), rank.getPrize(), rank.isBonusWin(), frequency
        );
    }

    public boolean isSecondPlace() {
        return this.matchCount == SECOND_PLACE_COUNT && isBonusWin;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    @Override
    public int compareTo(LottoWinningResponse other) {
        return Integer.compare(this.prize, other.prize);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoWinningResponse that = (LottoWinningResponse)o;
        return matchCount == that.matchCount && isBonusWin == that.isBonusWin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, isBonusWin);
    }

}
