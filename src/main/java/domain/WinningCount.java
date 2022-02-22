package domain;

import java.util.Objects;

public class WinningCount {
    private final int count;

    public int getCount() {
        return count;
    }

    public WinningCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        WinningCount that = (WinningCount) object;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "WinningCount{" +
                "count=" + count +
                '}';
    }
}
