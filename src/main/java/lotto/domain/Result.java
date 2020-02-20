package lotto.domain;

import java.util.Map;

public class Result {
    private Map<Rank, Integer> winningResults;

    Result(Map<Rank, Integer> winningResults) {
        this.winningResults = winningResults;
    }

    public Map<Rank, Integer> getWinningResults() {
        return winningResults;
    }

    /* winningTicketSize은 각 Rank별 당첨된 티켓 수를 의미한다. */
    Map<Rank, Integer> addWinningResult(Rank rank, int winningTicketSize){
        winningResults.put(rank, winningTicketSize);
        return winningResults;
    }
}
