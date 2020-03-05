package lotto.domain;

public class ResultsDTO {
    private final MatchResults matchResults;
    private final long earningRate;

    public ResultsDTO(MatchResults matchResults, long earningRate) {
        this.matchResults = matchResults;
        this.earningRate = earningRate;
    }

    public int getMatchCount(WinningInfo winningInfo) {
        return this.matchResults.getMatchCount(winningInfo);
    }

    public long getEarningRate() {
        return earningRate;
    }
}
