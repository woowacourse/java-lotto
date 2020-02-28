package domain;

import java.util.stream.IntStream;

public class RepeatCount {
    private static final String ERROR_NEGATIVE_MESSAGE = "반복 횟수는 음수일 수 없습니다.";
    private static final String ERROR_EXCEED_MESSAGE = "수동 횟수가 총 횟수를 초과할 수 없습니다.";

    private int repeatCount;

    public RepeatCount(int repeatCount) {
        validateRepeatCount(repeatCount);
        this.repeatCount = repeatCount;
    }

    private void validateRepeatCount(int repeatCount) {
        if (repeatCount < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_MESSAGE);
        }
    }

    public RepeatCount split(int repeatCount) {
        validateSplitCount(repeatCount);
        this.repeatCount -= repeatCount;
        return new RepeatCount(repeatCount);
    }

    private void validateSplitCount(int repeatCount) {
        if (this.repeatCount - repeatCount < 0) {
            throw new IllegalArgumentException(ERROR_EXCEED_MESSAGE);
        }
    }

    public IntStream toIntStream() {
        return IntStream.range(0, repeatCount);
    }

    public boolean isNotZero() {
        return repeatCount != 0;
    }

    @Override
    public String toString() {
        return String.valueOf(repeatCount);
    }
}
