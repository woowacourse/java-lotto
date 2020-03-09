package lotto.util;

public class Count {
    private long count;

    public Count(long count) {
        validateRange(count);
        this.count = count;
    }

    private void validateRange(long count) {
        if (count < 0) {
            throw new IllegalArgumentException("횟수 또는 갯수는 음수일 수 없습니다.");
        }
    }

    public Count subtract(Count count) {
        return new Count(this.count - count.count);
    }

    public long getCount() {
        return count;
    }
}
