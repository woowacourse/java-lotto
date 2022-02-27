package domain;

import java.util.*;
import java.util.stream.Collectors;

public class PrizeResult {

    private final Map<Rank, Integer> prizeResult;
    private final float earningRate;

    public PrizeResult(int inputMoney, List<Lotto> lottos, WinningNumber winningNumber) {
        this.prizeResult = new HashMap<>();
        initFinalResult();
        calculatePrizeResult(lottos, winningNumber);
        this.earningRate = calculateEarningRate(inputMoney);
    }

    private void initFinalResult() {
        Rank.getWinnerPrices()
                .forEach(winnerPrice -> prizeResult.put(winnerPrice, 0));
    }

    private void calculatePrizeResult(List<Lotto> lottos, WinningNumber winningNumber) {
        for (Lotto lotto : lottos) {
            Rank winnerPrice = lotto.calculateRank(winningNumber);
            updatePrizeResult(winnerPrice);
        }
    }

    private void updatePrizeResult(Rank winnerPrice) {
        prizeResult.put(winnerPrice, prizeResult.get(winnerPrice) + 1);
    }

    private float calculateEarningRate(int inputMoney) {
        float earningRate = (float) totalPrize() / inputMoney;
        return  (float) (Math.floor(earningRate * 100) / 100.0);
    }

    private int totalPrize() {
        int totalPrice = 0;
        for (Rank winnerPrice : prizeResult.keySet()) {
            totalPrice += winnerPrice.getPrize() * prizeResult.get(winnerPrice);
        }
        return totalPrice;
    }

    public List<Rank> sortedPriceKeySet() {
        return prizeResult.keySet().stream()
                .filter(winnerPrice -> winnerPrice != Rank.FAIL)
                .sorted(Comparator.comparing(Rank::getPrize))
                .collect(Collectors.toList());
    }

    public Map<Rank, Integer> getPrizeResult() {
        return Collections.unmodifiableMap(prizeResult);
    }

    public float getEarningRate() {
        return earningRate;
    }

}
