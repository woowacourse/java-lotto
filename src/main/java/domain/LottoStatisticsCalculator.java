package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public class LottoStatisticsCalculator {
    
    private final List<Lotto> lottos;
    
    public LottoStatisticsCalculator(List<Lotto> lottos) {
        this.lottos = lottos;
    }
    
    public EnumMap<LottoPrize, Integer> statisticsCalculate(List<Integer> matchNumbers, int bonusNumber) {
        EnumMap<LottoPrize, Integer> enumMap = new EnumMap<>(LottoPrize.class);
        
        for (LottoPrize value : LottoPrize.values()) {
            enumMap.put(value, 0);
        }
        
        for (Lotto lotto : lottos) {
            int matchCount = lotto.getMatchCount(matchNumbers);
            boolean bonusMatch = lotto.isBonusMatch(bonusNumber);
            
            Optional<LottoPrize> matchPrize = LottoPrize.match(matchCount, bonusMatch);
            matchPrize.ifPresent(lottoPrize -> enumMap.put(lottoPrize, enumMap.get(lottoPrize) + 1));
        }
        
        return enumMap;
    }
}
