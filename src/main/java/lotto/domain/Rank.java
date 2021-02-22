package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum Rank {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4, 50_000L),
    FIFTH(3, 5_000L),
    NOTHING(0, 0L);
    
    public static final Comparator<Rank> ASCENDING_ORDER = Comparator.comparingInt(Rank::getMatchCount)
                                                                     .thenComparing(Rank::getReward);
    
    private final int matchCount;
    
    private final long reward;
    
    Rank(int matchCount, long reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }
    
    public static Rank searchRank(WinningLotto winningLotto, Lotto lotto) {
        long matchCount = winningLotto.countMatchingNumber(lotto);
        
        boolean hasBonusNumber = winningLotto.checkThatLottoHasBonusNumber(lotto);
        if (hasBonusNumber) {
            matchCount++;
        }
        
        return Rank.searchRank(matchCount, hasBonusNumber);
    }
    
    private static Rank searchRank(long matchCount, boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                     .filter(rank -> hasSameMatchCount(rank, matchCount))
                     .map(rank -> mapIfMatchCountIsFive(rank, hasBonusNumber))
                     .findFirst()
                     .orElse(Rank.NOTHING);
    }
    
    private static boolean hasSameMatchCount(Rank rank, long matchCount) {
        return rank.matchCount == matchCount;
    }
    
    private static Rank mapIfMatchCountIsFive(Rank rank, boolean hasBonusNumber) {
        if (hasSameMatchCount(Rank.FIRST, rank.matchCount) && hasBonusNumber) {
            return SECOND;
        }
        
        if (hasSameMatchCount(Rank.SECOND, rank.matchCount)) {
            return THIRD;
        }
        
        return rank;
    }
    
    public long getReward() {
        return reward;
    }
    
    public int getMatchCount() {
        return matchCount;
    }
}
