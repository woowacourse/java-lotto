package lotto.dto;

import java.util.Objects;

import lotto.domain.LottoRank;

public class LottoWinningResponse {

    private static final int SECOND_PLACE_COUNT = 5;

    private final int matchCount;
    private final int prize;
    private final boolean isBonusWin;

    public LottoWinningResponse(int matchCount, int prize, boolean isBonusWin) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.isBonusWin = isBonusWin;
    }

    public static LottoWinningResponse from(LottoRank rank) {
        return new LottoWinningResponse(rank.getMatchCount(), rank.getPrize(), rank.isBonusWin());
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
