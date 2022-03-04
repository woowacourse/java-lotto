package domain;

public class Count {

    private static final int COUNT_END_NUMBER = 0;
    private static final int COUNT_DECREASE_UNIT = 1;
    private static final String MANUAL_COUNT_CANNOT_EXCEED_TOTAL_MESSAGE = "투입 금액보다 더 많은 갯수를 선택할 수 없습니다.";

    private final int count;

    public Count(final int count) {
        this.count = count;
    }

    public static Count createWithTotal(final int manualCount, final int totalCount) {
        validate(manualCount, totalCount);
        return new Count(manualCount);
    }

    private static void validate(final int manualCount, final int totalCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException(MANUAL_COUNT_CANNOT_EXCEED_TOTAL_MESSAGE);
        }
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
