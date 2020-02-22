package lotto.domain;

import java.util.Objects;

public class Prize {

    private static final long MINIMUM_PRIZE = 0L;
    private static final String INVALID_PRIZE_EXCEPTION_MESSAGE = "유효하지 않는 금액입니다.";

    private final long prize;

    public Prize(long prize) {
        if (prize <= MINIMUM_PRIZE) {
            throw new IllegalArgumentException(INVALID_PRIZE_EXCEPTION_MESSAGE);
        }
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }

    public long multiply(int count) {
        return prize * count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Prize prize1 = (Prize) o;
        return prize == prize1.prize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prize);
    }
}
