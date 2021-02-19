package lotto.domain;

import java.util.Comparator;

public enum RankComparator implements Comparator<Rank> {
    INSTANCE;
    
    @Override
    public int compare(Rank preRank, Rank postRank) {
        int valueOfComparedMatchCount = Rank.compareMatchCount(preRank, postRank);
        
        if (isSameMatchCount(valueOfComparedMatchCount)) {
            return Rank.compareReward(preRank, postRank);
        }
        
        return valueOfComparedMatchCount;
    }
    
    private boolean isSameMatchCount(int valueOfComparedMatchCount) {
        return valueOfComparedMatchCount == 0;
    }
}
