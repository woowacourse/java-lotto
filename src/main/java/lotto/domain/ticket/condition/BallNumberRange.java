package lotto.domain.ticket.condition;

public enum BallNumberRange {

    RANGE_INCLUSIVE_START(1),
    RANGE_INCLUSIVE_END(45);

    private final int number;

    BallNumberRange(final int number) {
        this.number = number;
    }

    public static boolean isOutOfRange(final int ballNumber) {
        return (ballNumber < RANGE_INCLUSIVE_START.number) || (RANGE_INCLUSIVE_END.number < ballNumber);
    }

}
