package domain;

import java.util.*;
import java.util.stream.Collectors;

public class PrizeResult {

    private final Map<Prize, Integer> prizeResult;
    private final float earningRate;

    public PrizeResult(int inputMoney, List<Lotto> lottos, WinningNumber winningNumber) {
        this.prizeResult = new HashMap<>();
        initFinalResult();
        calculatePrizeResult(lottos, winningNumber);
        this.earningRate = calculateEarningRate(inputMoney);
    }

    private void initFinalResult() {
        Prize.getWinnerPrices()
                .forEach(winnerPrice -> prizeResult.put(winnerPrice, 0));
    }

    private void calculatePrizeResult(List<Lotto> lottos, WinningNumber winningNumber) {
        for (Lotto lotto : lottos) {
            Prize winnerPrice = lotto.calculateRank(winningNumber);
            updatePrizeResult(winnerPrice);
        }
    }

    private void updatePrizeResult(Prize winnerPrice) {
        prizeResult.put(winnerPrice, prizeResult.get(winnerPrice) + 1);
    }

    private float calculateEarningRate(int inputMoney) {
        long totalPrize = totalPrize();

        float earningRate = (float) totalPrize / inputMoney;
        return  (float) (Math.floor(earningRate * 100) / 100.0);
    }

    private int totalPrize() {
        int totalPrice = 0;
        for (Prize winnerPrice : prizeResult.keySet()) {
            totalPrice += winnerPrice.getPrize() * prizeResult.get(winnerPrice);
        }
        return totalPrice;
    }

    public List<Prize> validWinnerPrices() {
        return prizeResult.keySet().stream()
                .filter(winnerPrice -> winnerPrice != Prize.FAIL)
                .sorted(Comparator.comparing(Prize::getPrize))
                .collect(Collectors.toList());
    }

    public Map<Prize, Integer> getPrizeResult() {
        return Collections.unmodifiableMap(prizeResult);
    }

    public float getEarningRate() {
        return earningRate;
    }
}
