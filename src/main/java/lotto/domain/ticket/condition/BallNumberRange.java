package lotto.domain.ticket.condition;

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

    public static int getInclusiveRangeStart() {
        return INCLUSIVE_RANGE_START.number;
    }

    public static int getExclusiveRangeEnd() {
        return INCLUSIVE_RANGE_END.number + 1;
    }

}
