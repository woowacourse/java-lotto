package domain;

import java.util.*;
import java.util.stream.Collectors;

public class PrizeResult {

    private Map<Prize, Integer> prizeResult;

    public PrizeResult() {
        this.prizeResult = new HashMap<>();
        initFinalResult();
    }

    private void initFinalResult() {
        Prize.getWinnerPrices()
                .forEach(winnerPrice -> prizeResult.put(winnerPrice, 0));
    }

    public void updatePrizeResult(Prize winnerPrice) {
        prizeResult.put(winnerPrice, prizeResult.get(winnerPrice) + 1);
    }

    public int totalPrize() {
        int totalPrice = 0;
        for (Prize winnerPrice : prizeResult.keySet()) {
            totalPrice += winnerPrice.getPrize() * prizeResult.get(winnerPrice);
        }
        return totalPrice;
    }

    public Map<Prize, Integer> getPrizeResult() {
        return Collections.unmodifiableMap(prizeResult);
    }

    public List<Prize> validWinnerPrices() {
        return prizeResult.keySet().stream()
                .filter(winnerPrice -> winnerPrice != Prize.FAIL)
                .sorted(Comparator.comparing(Prize::getPrize))
                .collect(Collectors.toList());
    }

}
