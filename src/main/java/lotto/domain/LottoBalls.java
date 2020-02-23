package lotto.domain;

import lotto.Exception.NumberOutOfRangeException;
import lotto.util.InputValidationUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoBalls {
    public static final String DELIMITER = ",";

    public static Set<LottoBall> generateLottoBalls(String lottoBallsInput){
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
        if (winningBalls.length != 6) {
            throw new NumberOutOfRangeException("6개의 숫자를 입력해주세요");
        }
    }
}
