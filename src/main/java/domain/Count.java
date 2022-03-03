package domain;

public class Count {

    private static final int COUNT_END_NUMBER = 0;
    private static final int COUNT_DECREASE_UNIT = 1;

    private final int count;

    public Count(final int count) {
        this.count = count;
    }

    public boolean isEnd() {
        return this.count <= COUNT_END_NUMBER;
    }

    public Count decrease() {
        return new Count(this.count - COUNT_DECREASE_UNIT);
    }

    public int getCount() {
        return this.count;
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
