package lotto.domain.game;

import java.util.Objects;

public class AutoCount {
    private final int count;

    public AutoCount(int count) {
        this.count = count;
    }

    int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoCount autoCount = (AutoCount) o;
        return count == autoCount.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
