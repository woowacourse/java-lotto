package model;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class LottoEvaluator {
    private final WinningLotto winningLotto;

    public LottoEvaluator(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public Map<Prize, Integer> getResult(Lottos lottos) {
        Map<Prize, Integer> result = new TreeMap<>();
        Arrays.stream(Prize.values()).forEach(prize -> result.put(prize, 0));
        lottos.stream().forEach(
                lotto -> lotto
                        .calculatePrize(winningLotto)
                        .ifPresent(prize -> result.put(prize, result.get(prize) + 1))
        );
        return result;
    }

    public double computeProfit(Lottos lottos) {
        Map<Prize, Integer> result = getResult(lottos);
        int sum = 0;
        for (var entry : result.entrySet()) {
            sum += entry.getKey().price * entry.getValue();
        }
        return sum / (lottos.computeTicketCount() * 1000.0);
    }
}
