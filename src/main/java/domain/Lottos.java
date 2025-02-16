package domain;

import dto.LottoMatchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<LottoPrize, Integer> getStatistics(final List<Integer> matchNumbers, final int bonusNumber) {
        return calculateStatistics(matchNumbers, bonusNumber);
    }

    public double getIncomeRate(final List<Integer> matchNumbers, final int bonusNumber, final int money) {
        Map<LottoPrize, Integer> prizeMap = calculateStatistics(matchNumbers, bonusNumber);
        final long totalIncome = calculateTotalIncome(prizeMap);
        return (double) totalIncome / money;
    }

    private long calculateTotalIncome(final Map<LottoPrize, Integer> prizeMap) {
        long sum = 0L;
        for (Map.Entry<LottoPrize, Integer> entry : prizeMap.entrySet()) {
            sum += entry.getKey().getPrizeMoney() * entry.getValue();
        }
        return sum;
    }

    private Map<LottoPrize, Integer> calculateStatistics(final List<Integer> matchNumbers, final int bonusNumber) {
        Map<LottoPrize, Integer> lottoPrizeMap = LottoPrize.getInitailizedEnumMap();
        insertLottoPrizeResult(matchNumbers, bonusNumber, lottoPrizeMap);
        return lottoPrizeMap;
    }

    private void insertLottoPrizeResult(final List<Integer> matchNumbers, final int bonusNumber,
                                        final Map<LottoPrize, Integer> enumMap) {

        for (Lotto lotto : lottos) {
            final LottoMatchResult matchResult = lotto.getMatchResult(matchNumbers, bonusNumber);
            final LottoPrize matchPrize = LottoPrize.match(matchResult);

            enumMap.put(matchPrize, enumMap.getOrDefault(matchPrize, 0) + 1);
        }
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
