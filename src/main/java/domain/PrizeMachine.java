package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrizeMachine {

    private List<Lotto> lottos;
    private Map<Prize, Integer> prizeResult;

    public PrizeMachine(List<Lotto> lottos) {
        this.prizeResult = new HashMap<>();
        this.lottos = lottos;
        initFinalResult();
    }

    private void initFinalResult() {
        Prize.getWinnerPrices()
                .forEach(winnerPrice -> prizeResult.put(winnerPrice, 0));
    }

    public Map<Prize, Integer> calculatePrizeResult(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            Prize prize = winningLotto.calculatePrize(lotto);
            prizeResult.put(prize, prizeResult.get(prize) + 1);
        }
        return Collections.unmodifiableMap(prizeResult);
    }

    public double calculateEarningRate() {
        int totalPrize = 0;
        for (Prize winnerPrice : prizeResult.keySet()) {
            totalPrize += winnerPrice.getPrize() * prizeResult.get(winnerPrice);
        }
        double earningRate = (double)totalPrize / (lottos.size() * Lotto.PRICE);
        return Math.floor(earningRate * 100) / 100.0;
    }

}
