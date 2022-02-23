package model;

import java.util.Map;
import java.util.Objects;

public class WinningCount {
    private final int matchCount;

    public WinningCount(int matchCount, boolean matchBonus) {
        this.matchCount = matchCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningCount that = (WinningCount) o;
        return matchCount == that.matchCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount);
    }
}
