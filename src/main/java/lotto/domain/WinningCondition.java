package lotto.domain;

public class WinningCondition {

    private final int matchedCount;
    private final boolean isBonusNumberMatched;

    public WinningCondition(int matchedCount, boolean isBonusNumberMatched) {
        this.matchedCount = matchedCount;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    public boolean isWinningCondition(int matches, boolean aaa) {
        if (matchedCount != matches) {
            return false;
        }

        if (!isBonusNumberMatched) {
            if (aaa) {
                return true;
            }
        }
        return false;
    }
}
