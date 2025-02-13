package constant;

public enum WinningCount {
    NONE(0,0),
    THREE(3,5000),
    FOUR(4,50000),
    FIVE(5,1500000),
    FIVE_BONUS(5,30000000),
    SIX(6,2000000000)
    ;
    private int amount;
    private int matchedCount;

    WinningCount(int matchedCount,int amount) {
        this.matchedCount = matchedCount;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
    public int getMatchedCount() {
        return matchedCount;
    }
}
