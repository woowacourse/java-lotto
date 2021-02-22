package lotto.domain.rating;

import java.util.Objects;

public class Counter {

    private final int count;

    public Counter() {
        this(0);
    }

    public Counter(final int count) {
        this.count = count;
    }

    public Counter plus() {
        return new Counter(count + 1);
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Counter counter = (Counter) o;
        return count == counter.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
