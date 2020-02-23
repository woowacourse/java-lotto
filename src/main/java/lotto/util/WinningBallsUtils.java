package lotto.util;

import lotto.Exception.NumberOutOfRangeException;
import lotto.domain.LottoBall;
import lotto.domain.LottoBallFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningBallsUtils {
    public static final String DELIMITER = ",";

    private Set<LottoBall> winningBalls;

    public WinningBallsUtils(String input) {
        String[] winningBalls = input.split(DELIMITER);
        validateWinningBallsNumber(winningBalls);
        validateWinningBallsLength(winningBalls);
        this.winningBalls = Arrays.stream(winningBalls)
                .map(Integer::parseInt)
                .map(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toSet());
    }

    private void validateWinningBallsNumber(String[] winningBalls) {
        for (String winningBall : winningBalls) {
            InputValidationUtil.returnNumberWithNumberCheck(winningBall);
        }
    }

    private void validateWinningBallsLength(String[] winningBalls) {
        if (winningBalls.length != 6) {
            throw new NumberOutOfRangeException("6개의 숫자를 입력해주세요");
        }
    }

    public Set<LottoBall> getWinningBalls() {
        return Collections.unmodifiableSet(new HashSet<>(this.winningBalls));
    }
}
