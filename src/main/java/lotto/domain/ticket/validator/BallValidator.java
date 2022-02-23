package lotto.domain.ticket.validator;

public class BallValidator {

    private static final int BALL_RANGE_INCLUSIVE_START = 1;
    private static final int BALL_RANGE_INCLUSIVE_END = 45;
    private static final String BALL_OUT_OF_RANGE_EXCEPTION_MESSAGE = "번호의 범위는 1부터 45까지여야 합니다.";

    private static final BallValidator INSTANCE = new BallValidator();

    private BallValidator() {
    }

    public static void validateBallNumber(final int number) {
        INSTANCE.validateOutOfRange(number);
    }

    private void validateOutOfRange(final int number) {
        if (number < BALL_RANGE_INCLUSIVE_START || number > BALL_RANGE_INCLUSIVE_END) {
            throw new IllegalArgumentException(BALL_OUT_OF_RANGE_EXCEPTION_MESSAGE);
        }
    }

}
