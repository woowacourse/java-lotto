package domain;

import dto.LottoMatchResult;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Lottos {
    
    private final List<Lotto> lottos;
    
    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }
    
    public EnumMap<LottoPrize, Integer> getStatistics(List<Integer> matchNumbers, int bonusNumber) {
        return calculateStatistics(matchNumbers, bonusNumber);
    }
    
    public double getIncomeRate(List<Integer> matchNumbers, int bonusNumber, int money) {
        EnumMap<LottoPrize, Integer> prizeMap = calculateStatistics(matchNumbers, bonusNumber);
        long totalIncome = calculateTotalIncome(prizeMap);
        return (double) totalIncome / money;
    }
    
    private long calculateTotalIncome(EnumMap<LottoPrize, Integer> prizeMap) {
        long sum = 0L;
        for (Map.Entry<LottoPrize, Integer> entry : prizeMap.entrySet()) {
            sum += entry.getKey().getPrizeMoney() * entry.getValue();
        }
        return sum;
    }
    
    private EnumMap<LottoPrize, Integer> calculateStatistics(List<Integer> matchNumbers, int bonusNumber) {
        EnumMap<LottoPrize, Integer> enumMap = new EnumMap<>(LottoPrize.class);
        
        for (LottoPrize value : LottoPrize.values()) {
            enumMap.put(value, 0);
        }
        
        for (Lotto lotto : lottos) {
            LottoMatchResult matchResult = lotto.getMatchResult(matchNumbers, bonusNumber);
            Optional<LottoPrize> matchPrize = LottoPrize.match(matchResult);
            matchPrize.ifPresent(lottoPrize -> enumMap.put(lottoPrize, enumMap.get(lottoPrize) + 1));
        }
        
        return enumMap;
    }
}
