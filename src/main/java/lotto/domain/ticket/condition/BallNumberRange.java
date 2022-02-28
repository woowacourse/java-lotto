package lotto.domain.ticket.condition;

import java.util.stream.IntStream;

public enum BallNumberRange {

    INCLUSIVE_RANGE_START(1),
    INCLUSIVE_RANGE_END(45);

    private final int number;

    BallNumberRange(final int number) {
        this.number = number;
    }

    public static boolean isOutOfRange(final int ballNumber) {
        return (ballNumber < INCLUSIVE_RANGE_START.number) || (INCLUSIVE_RANGE_END.number < ballNumber);
    }

    public static IntStream getBallNumbers() {
        return IntStream.range(INCLUSIVE_RANGE_START.number, INCLUSIVE_RANGE_END.number + 1);
    }

}
