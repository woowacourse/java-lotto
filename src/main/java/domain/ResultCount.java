package domain;

import java.util.Objects;

public class ResultCount {
    int count;

    public ResultCount() {
        count = 0;
    }

    public void add() {
        count++;
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
}
