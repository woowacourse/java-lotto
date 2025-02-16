package model;

public enum RankType {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private int matchCount;
    private int price;

    RankType(int winningCount, int price) {
        this.matchCount = winningCount;
        this.price = price;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }

    private static RankType checkSecondAndThird(boolean bonusBall){
        if(bonusBall) return SECOND;
        return THIRD;
    }

    public static RankType calculateRankType(boolean isBonusMatch, int matchCount){
        if(matchCount == SECOND.matchCount){
            return checkSecondAndThird(isBonusMatch);
        }
        for(RankType rankType : values()){
            if(rankType.matchCount == matchCount) return rankType;
        }
        return NONE;
    }


}
