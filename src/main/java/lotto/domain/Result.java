package lotto.domain;

import java.util.Map;

public class Result {
    private static final int WINNING_AMOUNT = 1;
    // todo: 이게 무슨 의미인 지 조금 더 명확하면 좋겠다.
    private static final int DEFAULT_WINNING_TICKET_SIZE = 0;
    private Map<Rank, Integer> winningResults;

    Result(Map<Rank, Integer> winningResults) {
        this.winningResults = winningResults;
    }

    public Map<Rank, Integer> getWinningResults() {
        return winningResults;
    }

    /* winningTicketSize은 각 Rank별 당첨된 티켓 수를 의미한다. */
    Map<Rank, Integer> addWinningResult(Rank rank) {
        Integer winningTicketSize = winningResults.get(rank);
        if (winningTicketSize == null) {
            winningTicketSize = DEFAULT_WINNING_TICKET_SIZE;
        }

        winningResults.put(rank, winningTicketSize + WINNING_AMOUNT);
        return winningResults;
    }
}
