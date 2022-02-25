package vo;

import java.util.Objects;

public class WinningCount {
    private static final int MINIMUM_WINNING_COUNT = 0;
    private static final String ERROR_MESSAGE_FOR_MINUS_WINNING_COUNT = "[ERROR] 당첨 횟수는 음수일 수 없습니다.";

    private final int count;

    public WinningCount(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (count < MINIMUM_WINNING_COUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_MINUS_WINNING_COUNT);
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
        return "WinningCount{" +
                "count=" + count +
                '}';
    }
}
