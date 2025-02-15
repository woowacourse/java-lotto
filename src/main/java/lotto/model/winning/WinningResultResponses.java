package lotto.model.winning;

import java.util.List;

public class WinningResultResponses {

    private final List<WinningResultResponse> responses;

    public WinningResultResponses(List<WinningResultResponse> responses) {
        this.responses = responses;
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
