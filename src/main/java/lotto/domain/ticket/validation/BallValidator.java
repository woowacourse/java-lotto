package lotto.domain.ticket.validation;

import lotto.domain.ticket.condition.BallNumberRange;
import lotto.exception.LottoException;
import lotto.exception.ball.BallNumberExceptionStatus;

public class BallValidator {

    private static final BallValidator INSTANCE = new BallValidator();

    private BallValidator() {
    }

    public static void validateBallNumber(final int ballNumber) {
        INSTANCE.verifyBallNumberNotOutOfRange(ballNumber);
    }

    private void verifyBallNumberNotOutOfRange(final int ballNumber) {
        if (BallNumberRange.isOutOfRange(ballNumber)) {
            throw new LottoException(BallNumberExceptionStatus.BALL_CANNOT_BE_OUT_OF_RANGE);
        }
    }

}
