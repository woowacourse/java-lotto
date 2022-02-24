package lotto.domain;

import java.util.Arrays;
import java.util.Map;

public class LottoResults {

    private static final String ERROR_NULL_ARGUMENT_MESSAGE = "로또 결과 생성자의 인자는 null이면 안됩니다.";
    private static final String ERROR_RESULT_SIZE_MESSAGE = "로또 결과가 잘못되었습니다.";

    private final Map<LottoPrize, Integer> results;

    public LottoResults(Map<LottoPrize, Integer> results) {
        validateNull(results);
        validMissingKey(results);

        this.results = results;
    }

    private void validateNull(Map<LottoPrize, Integer> results) {
        if (results == null) {
            throw new NullPointerException(ERROR_NULL_ARGUMENT_MESSAGE);
        }
    }

    private void validMissingKey(Map<LottoPrize, Integer> results) {
        int keySize = (int) Arrays.stream(LottoPrize.values())
                .filter(results::containsKey)
                .count();
        if (keySize != LottoPrize.values().length) {
            throw new IllegalArgumentException(ERROR_RESULT_SIZE_MESSAGE);
        }
    }

    public int getPrizeNumber(LottoPrize prize) {
        return results.get(prize);
    }

    public double getRateReturn() {
        int totalSpendMoney = 0;
        int totalReward = 0;

        for (LottoPrize prize : LottoPrize.values()) {
            int prizeNumber = results.get(prize);
            totalSpendMoney += prizeNumber * 1000;
            totalReward += prize.getTotalReward(prizeNumber);
        }

        return (double) totalReward / totalSpendMoney;
    }
}
