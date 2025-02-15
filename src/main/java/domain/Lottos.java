package domain;

import dto.LottoMatchResult;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<LottoPrize, Integer> getStatistics(List<Integer> matchNumbers, int bonusNumber) {
        return calculateStatistics(matchNumbers, bonusNumber);
    }

    public double getIncomeRate(List<Integer> matchNumbers, int bonusNumber, int money) {
        Map<LottoPrize, Integer> prizeMap = calculateStatistics(matchNumbers, bonusNumber);
        long totalIncome = calculateTotalIncome(prizeMap);
        return (double) totalIncome / money;
    }

    private long calculateTotalIncome(Map<LottoPrize, Integer> prizeMap) {
        long sum = 0L;
        for (Map.Entry<LottoPrize, Integer> entry : prizeMap.entrySet()) {
            sum += entry.getKey().getPrizeMoney() * entry.getValue();
        }
        return sum;
    }

    private Map<LottoPrize, Integer> calculateStatistics(List<Integer> matchNumbers, int bonusNumber) {
        Map<LottoPrize, Integer> lottoPrizeMap = LottoPrize.getInitailizedEnumMap();
        insertLottoPrizeResult(matchNumbers, bonusNumber, lottoPrizeMap);
        return lottoPrizeMap;
    }

    private void insertLottoPrizeResult(List<Integer> matchNumbers, int bonusNumber, Map<LottoPrize, Integer> enumMap) {

        for (Lotto lotto : lottos) {
            LottoMatchResult matchResult = lotto.getMatchResult(matchNumbers, bonusNumber);
            LottoPrize matchPrize = LottoPrize.match(matchResult);

            enumMap.put(matchPrize, enumMap.getOrDefault(matchPrize, 0) + 1);
        }
    }
}
