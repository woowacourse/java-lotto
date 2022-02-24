package lotto.domain.ticket.condition;

import java.util.Collections;
import java.util.List;

public enum BallNumberDuplication {

    ALLOWED_MAXIMUM_COUNT(1);

    private final int count;

    BallNumberDuplication(final int count) {
        this.count = count;
    }

    public static boolean isExcessiveDuplicated(final List<Integer> ballNumbers, final int ballNumber) {
        return Collections.frequency(ballNumbers, ballNumber) > ALLOWED_MAXIMUM_COUNT.count;
    }

}