package domain;

import java.util.Collections;
import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final Ball bonusBall;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        validateUniqueBall(lotto);
        this.lotto = lotto;
        this.bonusBall = new Ball(bonusNumber);
    }

    private void validateUniqueBall(Lotto lotto) {
        List<Ball> balls = lotto.getBalls();
        balls.forEach(ball -> {
            if (Collections.frequency(balls, ball) > 1) {
                throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
            }
        });
        if (balls.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

}
