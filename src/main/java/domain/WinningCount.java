package domain;

import java.util.Objects;

public class WinningCount {
    public static final int POSITIVE_CRITERIA = 0;
    public static final String ERROR_MESSAGE_FOR_NEGATIVE_OR_ZERO_NUMBER = "당첨 횟수는 0이상 이어야 합니다.";
    
    private final int count;

    public WinningCount(int count) {
        validateNotNegative(count);
        this.count = count;
    }

    private void validateNotNegative(int count) {
        if (count < POSITIVE_CRITERIA) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_NEGATIVE_OR_ZERO_NUMBER);
        }
    }

    public int getCount() {
        return count;
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
        return "WinningCount{" + "count=" + count + '}';
    }
}
