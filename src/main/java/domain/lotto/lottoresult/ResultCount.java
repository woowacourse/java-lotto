package domain.lotto.lottoresult;

import java.util.Objects;

public class ResultCount {
    private long count;

    public ResultCount(long count) {
        this.count = count;
    }

    public long multiply(int operand) {
        return (long) count * operand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (Objects.isNull(o) || getClass() != o.getClass()) {
            return false;
        }
        ResultCount that = (ResultCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }

    public static ResultCount sum(ResultCount count1, ResultCount count2) {
        return new ResultCount(count1.count + count2.count);
    }
}
