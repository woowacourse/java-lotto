package lotto.domain;

import java.util.List;

public class BonusBall {
    private Ball bonusBall;

    public BonusBall(String bonusBall) {
        validate(bonusBall);
        this.bonusBall = Ball.valueOf(Integer.parseInt(bonusBall));
    }

    private void validate(String bonusBall) {
        checkNoInput(bonusBall);
        checkType(bonusBall);
    }

    private void checkNoInput(String bonusBall) {
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

    public boolean isIncluded(List<Ball> balls) {
        return balls.stream()
                .anyMatch(ball -> ball.equals(bonusBall));
    }
}
