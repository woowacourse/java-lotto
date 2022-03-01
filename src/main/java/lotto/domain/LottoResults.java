package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class LottoResults {

    private static final String ERROR_NULL_ARGUMENT_MESSAGE = "로또 결과 생성자의 인자는 null이면 안됩니다.";

    private final Map<LottoPrize, Integer> results;

    public LottoResults(Map<LottoPrize, Integer> results) {
        Objects.requireNonNull(results, ERROR_NULL_ARGUMENT_MESSAGE);
        addMissingKey(results);

        this.results = results;
    }

    private void addMissingKey(Map<LottoPrize, Integer> results) {
        for (LottoPrize prize : LottoPrize.values()) {
            results.put(prize, results.getOrDefault(prize, 0));
        }
    }

    public double getRateReturn() {
        int totalSpendMoney = calculateTotalSpendMoney();
        if (totalSpendMoney == 0) {
            return 0;
        }
        return (double) calculateTotalReward() / totalSpendMoney;
    }

    private int calculateTotalSpendMoney() {
        int count = 0;
        for (Integer prizeCount : results.values()) {
            count += prizeCount;
        }
        return count * LottoGame.LOTTO_PRICE;
    }

    private int calculateTotalReward() {
        int totalReward = 0;
        for (LottoPrize prize : LottoPrize.values()) {
            totalReward += prize.getTotalReward(results.get(prize));
        }
        return totalReward;
    }

    public int getPrizeNumber(LottoPrize prize) {
        return results.get(prize);
    }
}
