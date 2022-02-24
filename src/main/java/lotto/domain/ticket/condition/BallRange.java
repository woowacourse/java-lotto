package lotto.domain.ticket.condition;

public enum BallRange {

    RANGE_INCLUSIVE_START(1),
    RANGE_INCLUSIVE_END(45);

    private final int number;

    BallRange(final int number) {
        this.number = number;
    }

    public static boolean isOutOfRange(final int ballNumber) {
        return (ballNumber < RANGE_INCLUSIVE_START.number) || (RANGE_INCLUSIVE_END.number < ballNumber);
    }

}
