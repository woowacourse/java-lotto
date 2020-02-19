package lotto.util;

import lotto.domain.LottoBall;

import java.util.*;
import java.util.stream.Collectors;

public class WinningBallsUtils {
    private List<LottoBall> winningBalls;

    public WinningBallsUtils(String input) {
        String[] winningBalls = input.split(",");
        validateWinningBallsNumber(winningBalls);
        validateWinningBallsLength(winningBalls);
        validateDuplicatedWinningBalls(winningBalls);
        this.winningBalls = Arrays.stream(winningBalls)
                .map(Integer::parseInt)
                .map(LottoBall::new)
                .collect(Collectors.toList());
        Collections.sort(this.winningBalls);
    }

    public void validateWinningBallsNumber(String[] winningBalls) {
        for (String winningBall : winningBalls) {
            InputValidationUtil.isNumber(winningBall);
        }
    }

    public void validateWinningBallsLength(String[] winningBalls) {
        if (winningBalls.length != 6) {
            throw new IllegalArgumentException("6개의 숫자를 입력해주세요");
        }
    }

    public void validateDuplicatedWinningBalls(String[] winningBalls){
        Set<String> DuplicatedValidation = new HashSet<>(Arrays.asList(winningBalls));
        if(DuplicatedValidation.size() != winningBalls.length){
            throw new IllegalArgumentException("중복값을 제거하고 입력해주세요.");
        }
    }

    public List<LottoBall> getWinningBalls() {
        return Collections.unmodifiableList(this.winningBalls);
    }
}
