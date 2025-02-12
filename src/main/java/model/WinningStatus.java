package model;

public enum WinningStatus {
    FIRST(2_000_000_000, 6, false, "6개 일치 (2000000000원)"),
    SECOND(30_000_000, 5, true, "5개 일치, 보너스 볼 일치(30000000원)"),
    THIRD(1_500_000, 5, false, "5개 일치 (1500000원)"),
    FOURTH(50_000, 4, false, "4개 일치 (50000원)"),
    FIFTH(5_000, 3, false, "3개 일치 (5000원)"),
    NONE(0, 0, false, "");

    private final int price;
    private final int matchingCount;
    private final boolean matchesBonusNumber;
    private final String expression;

    WinningStatus(int price, int matchingCount, boolean matchesBonusNumber, String expression){
        this.price = price;
        this.matchingCount = matchingCount;
        this.matchesBonusNumber = matchesBonusNumber;
        this.expression = expression;
    }

    public static WinningStatus findBy(int matchingCount, boolean matchesBonusNumber) {
        for(WinningStatus winningStatus : WinningStatus.values()) {
            if(winningStatus.matchingCount == matchingCount) {
                if(matchingCount == THIRD.matchingCount) {
                    if(matchesBonusNumber) return SECOND;
                    return THIRD;
                }
                return winningStatus;
            }
        }
        return NONE;
    }

    public String getExpression() {
        return expression;
    }

    public int getPrice() {
        return price;
    }
}
