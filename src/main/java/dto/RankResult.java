package dto;

import domain.Rank;

public class RankResult {
    private final Rank rank;
    private final int matchCnt;

    private RankResult(Rank rank, int matchCnt) {
        this.rank = rank;
        this.matchCnt = matchCnt;
    }

    public static RankResult of(Rank rank, int matchCnt) {
        return new RankResult(rank, matchCnt);
    }

    public Rank getRank() {
        return rank;
    }

    public int getMatchCnt() {
        return matchCnt;
    }
}
