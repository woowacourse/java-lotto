package domain;

public class Count {
    private final int count;

    public Count(final int count) {
        this.count = count;
    }

    public boolean isEnd() {
        return this.count <= 0;
    }

    public Count decrease() {
        return new Count(this.count - 1);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Count count1 = (Count) object;

        return count == count1.count;
    }

    @Override
    public int hashCode() {
        return count;
    }
}
