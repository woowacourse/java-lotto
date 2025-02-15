package lotto.model.winning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WinningResultResponses {

    private final List<WinningResultResponse> responses;

    public WinningResultResponses(final Map<Rank, Integer> ranks) {
        this.responses = toResponses(ranks);
    }

    private List<WinningResultResponse> toResponses(final Map<Rank, Integer> ranks) {
        List<WinningResultResponse> resultResponses = new ArrayList<>();
        for (Rank rank : ranks.keySet()) {
            if (rank.isNone()) {
                continue;
            }
            resultResponses.add(
                    new WinningResultResponse(
                            rank.getMatchingCount(), rank.getWinningAmount(), rank.hasBonus(), ranks.get(rank)
                    ));
        }
        return resultResponses;
    }

    public List<WinningResultResponse> getResponses() {
        return responses;
    }

    public long calculateTotalReturn() {
        long totalReturnMoney = 0L;
        for (WinningResultResponse response : responses) {
            totalReturnMoney += response.getWinningAmount() * response.getWinningCount();
        }
        return totalReturnMoney;
    }

}
