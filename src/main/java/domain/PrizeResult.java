package domain;

import java.util.HashMap;
import java.util.Map;

public class PrizeResult {

    private Map<WinnerPrice, Integer> prizeResult;

    public PrizeResult() {
        this.prizeResult = new HashMap<>();
        initFinalResult();
    }

    private void initFinalResult() {
        WinnerPrice.getWinnerPrices().stream()
                .forEach(winnerPrice -> prizeResult.put(winnerPrice, 0));
    }

    public void updatePrizeResult(WinnerPrice winnerPrice) {
        prizeResult.put(winnerPrice, prizeResult.get(winnerPrice) + 1);
    }

    public int totalPrize() {
        int totalPrice = 0;
        for (WinnerPrice winnerPrice : prizeResult.keySet()) {
            totalPrice += winnerPrice.getPrize() * prizeResult.get(winnerPrice);
        }
        return totalPrice;
    }

}
