package lotto.domain.ball.validator;

import java.util.List;
import java.util.Objects;

public class BallsValidator {

    private static final int BALLS_DEFAULT_SIZE = 6;
    private static final String BALLS_NULL_POINTER_EXCEPTION_MESSAGE = "숫자 요소는 NULL이 아니어야 합니다.";
    private static final String BALLS_OUT_OF_SIZE_EXCEPTION_MESSAGE = "숫자 요소는 6개여야 합니다.";

    private static final BallsValidator INSTANCE = new BallsValidator();

    private BallsValidator() {
    }

    public static void validateBallNumbers(final List<Integer> numbers) {
        INSTANCE.validateNumbersNullPointer(numbers);
        INSTANCE.validateNumbersOutOfSize(numbers);
    }

    private void validateNumbersNullPointer(final List<Integer> numbers) {
        if (Objects.isNull(numbers)) {
            throw new IllegalArgumentException(BALLS_NULL_POINTER_EXCEPTION_MESSAGE);
        }
    }

    private void validateNumbersOutOfSize(final List<Integer> numbers) {
        if (numbers.size() != BALLS_DEFAULT_SIZE) {
            throw new IllegalArgumentException(BALLS_OUT_OF_SIZE_EXCEPTION_MESSAGE);
        }
    }

}
