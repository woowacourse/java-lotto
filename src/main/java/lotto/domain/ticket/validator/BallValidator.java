package lotto.domain.ticket.validator;

import lotto.exception.LottoException;
import lotto.exception.ball.BallNumberExceptionStatus;

public class BallValidator {

    private static final int BALL_RANGE_INCLUSIVE_START = 1;
    private static final int BALL_RANGE_INCLUSIVE_END = 45;

    private static final BallValidator INSTANCE = new BallValidator();

    private BallValidator() {
    }

    public static void validateBallNumber(final int number) {
        INSTANCE.validateOutOfRange(number);
    }

    private void validateOutOfRange(final int number) {
        if (number < BALL_RANGE_INCLUSIVE_START || number > BALL_RANGE_INCLUSIVE_END) {
            throw new LottoException(BallNumberExceptionStatus.BALL_IS_NOT_IN_RANGE);
        }
    }

}
