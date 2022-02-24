package lotto.domain;

import java.util.Map;

public class LottoResults {

    private static final String ERROR_NULL_ARGUMENT_MESSAGE = "로또 결과 생성자의 인자는 null이면 안됩니다.";

    private final Map<LottoPrize, Integer> results;

    public LottoResults(Map<LottoPrize, Integer> results) {
        validateNull(results);
        addMissingKey(results);

        this.results = results;
    }

    private void validateNull(Map<LottoPrize, Integer> results) {
        if (results == null) {
            throw new NullPointerException(ERROR_NULL_ARGUMENT_MESSAGE);
        }
    }

    private void addMissingKey(Map<LottoPrize, Integer> results) {
        for (LottoPrize prize : LottoPrize.values()) {
            results.put(prize, results.getOrDefault(prize, 0));
        }
    }

    public double getRateReturn() {
        int totalSpendMoney = 0;
        int totalReward = 0;

        for (LottoPrize prize : LottoPrize.values()) {
            int prizeNumber = results.get(prize);
            totalSpendMoney += prizeNumber * 1000;
            totalReward += prize.getTotalReward(prizeNumber);
        }

        if (totalSpendMoney == 0) {
            return 0;
        }

        return (double) totalReward / totalSpendMoney;
    }

    public int getPrizeNumber(LottoPrize prize) {
        return results.get(prize);
    }
}
