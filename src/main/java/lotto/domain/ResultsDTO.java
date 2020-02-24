package lotto.domain;

public class ResultsDTO {
    private MatchResults matchResults;
    private long earningRate;

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
