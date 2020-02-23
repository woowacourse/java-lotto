package lotto.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidateWinningTicketUtils {

    public static final int LOTTO_TICKET_SIZE = 6;

    private ValidateWinningTicketUtils(){
    }

    public static void validateWinningBallsNumber(String[] winningBalls) {
        for (String winningBall : winningBalls) {
            InputValidationUtil.returnNumberWithNumberCheck(winningBall);
            InputValidationUtil.isPositiveNumber(Integer.parseInt(winningBall));
        }
    }

    public static void validateWinningBallsLength(String[] winningBalls) {
        if (winningBalls.length != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("6개의 숫자를 입력해주세요");
        }
    }

    public static void validateDuplicatedWinningBalls(String[] winningBalls) {
        Set<String> duplicatedValidation = new HashSet<>(Arrays.asList(winningBalls));
        if (duplicatedValidation.size() != winningBalls.length) {
            throw new IllegalArgumentException("중복값을 제거하고 입력해주세요.");
        }
    }

}
