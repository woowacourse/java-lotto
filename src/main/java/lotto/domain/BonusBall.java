package lotto.domain;

import java.util.List;

public class BonusBall {
    private Ball bonusBall;

    public BonusBall(String bonusBall, WinningBalls winningBalls) {
        validate(bonusBall, winningBalls);
        this.bonusBall = Ball.valueOf(Integer.parseInt(bonusBall));
    }

    private void validate(String bonusBall, WinningBalls winningBalls) {
        checkEmpty(bonusBall);
        checkType(bonusBall);
        checkDuplicate(bonusBall, winningBalls);
    }

    private void checkDuplicate(String bonusBall, WinningBalls winningBalls) {
        if (winningBalls.contains(bonusBall)) {
            throw new RuntimeException("당첨 번호와 보너스 볼은 중복될 수 없습니다.");
        }
    }

    private void checkEmpty(String bonusBall) {
        if (bonusBall == null || bonusBall.trim().isEmpty()) {
            throw new RuntimeException("보너스 번호를 입력하지 않으셨습니다.");
        }
    }

    private void checkType(String bonusBall) {
        try {
            Integer.parseInt(bonusBall);
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자만 입력하시기 바랍니다.");
        }
    }

    public boolean hasIncluded(List<Ball> balls) {
        return balls.stream()
                .anyMatch(ball -> ball.equals(bonusBall));
    }
}
