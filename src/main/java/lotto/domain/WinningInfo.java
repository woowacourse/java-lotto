package lotto.domain;

public enum WinningInfo {
    FAIL(0, false, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private int winningCount;
    private boolean hasBonus;
    private int winningPrice;

    WinningInfo(int winningCount, boolean hasBonus, int winningPrice) {
        this.winningCount = winningCount;
        this.hasBonus = hasBonus;
        this.winningPrice = winningPrice;
    }

    public static WinningInfo valueOf(int winningCount, boolean hasBonus) {
        WinningInfo winningInfo = null;
        switch (winningCount) {
            case 3:
                winningInfo = WinningInfo.FIFTH;
                break;
            case 4:
                winningInfo = WinningInfo.FOURTH;
                break;
            case 5:
                if (hasBonus) {
                    winningInfo = WinningInfo.SECOND;
                    break;
                }
                winningInfo = WinningInfo.THIRD;
                break;
            case 6:
                winningInfo = WinningInfo.FIRST;
                break;
            default:
                winningInfo = WinningInfo.FAIL;
        }
        return winningInfo;
    }

    public int getWinningPrice(){
        return winningPrice;
    }

    public int getWinningCount() { return  winningCount; }

    public boolean getHasBonus() {return hasBonus; }
}
