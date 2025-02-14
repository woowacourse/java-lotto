package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WinningResult {
    private final Map<WinningStatus, Integer> winningResults = new LinkedHashMap<>();

    public WinningResult() {
        for (WinningStatus winningStatus : WinningStatus.getSorted()) {
            winningResults.put(winningStatus, 0);
        }
    }

    public void update(WinningStatus winningStatus) {
        winningResults.replace(winningStatus, winningResults.get(winningStatus) + 1);
    }

    public Map<WinningStatus, Integer> getWinningResults() {
        return Collections.unmodifiableMap(winningResults);
    }

    public double calculateEarningRate(Purchase purchase) {
        int totalPrice = calculateTotalPrice();
        return ((double) totalPrice)/ purchase.getAmount();
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for(WinningStatus winningStatus : winningResults.keySet()) {
            totalPrice += winningStatus.getPrice() * winningResults.get(winningStatus);
        }
        return totalPrice;
    }
}
