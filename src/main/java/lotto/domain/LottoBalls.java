package lotto.domain;

import lotto.Exception.NumberOutOfRangeException;
import lotto.util.InputValidationUtil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoBalls {
    public static final String DELIMITER = ",";
    public static final int WINNING_BALLS_LENGTH = 6;
    public static final String NUMBER_OUT_OF_RANGE_ERROR_MESSAGE = String.format("%d개의 숫자를 입력해주세요",
            WINNING_BALLS_LENGTH);

    public static Set<LottoBall> generateLottoBalls(String lottoBallsInput) {
        String[] lottoBalls = lottoBallsInput.split(DELIMITER);
        validateWinningBallsNumber(lottoBalls);
        validateWinningBallsLength(lottoBalls);
        return Arrays.stream(lottoBalls)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toSet());
    }

    private static void validateWinningBallsNumber(String[] winningBalls) {
        for (String winningBall : winningBalls) {
            InputValidationUtil.returnNumberWithNumberCheck(winningBall);
        }
    }

    private static void validateWinningBallsLength(String[] winningBalls) {
        if (winningBalls.length != WINNING_BALLS_LENGTH) {
            throw new NumberOutOfRangeException(NUMBER_OUT_OF_RANGE_ERROR_MESSAGE);
        }
    }
}