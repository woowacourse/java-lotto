package lotto.domain;

public class WinningCondition {

    private final int matchedCount;
    private final boolean isBonusMatchNeeded;

    public WinningCondition(int matchedCount, boolean isBonusNumberMatched) {
        this.matchedCount = matchedCount;
        this.isBonusMatchNeeded = isBonusNumberMatched;
    }

    public boolean isWinningCondition(int matches, boolean isBonusMatched) {
        if (matchedCount != matches) {
            return false;
        }

        if (isBonusMatchNeeded && !isBonusMatched) {
            return false;
        }

        return true;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public boolean isBonusMatchNeeded() {
        return isBonusMatchNeeded;
    }
}
