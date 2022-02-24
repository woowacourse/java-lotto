package lotto.domain.ticket.validator;

import lotto.domain.ticket.condition.BallNumberRange;
import lotto.exception.LottoException;
import lotto.exception.ball.BallNumberExceptionStatus;

public class BallValidator {

    private static final BallValidator INSTANCE = new BallValidator();

    private BallValidator() {
    }

    public static void validateBallNumber(final int ballNumber) {
        INSTANCE.validateOutOfRange(ballNumber);
    }

    private void validateOutOfRange(final int ballNumber) {
        if (BallNumberRange.isOutOfRange(ballNumber)) {
            throw new LottoException(BallNumberExceptionStatus.BALL_IS_NOT_IN_RANGE);
        }
    }

}
