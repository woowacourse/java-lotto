package lotto.domain;

import lotto.util.InputValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class WinningBalls {
    List<LottoBall> winningBalls;
    LottoBall bonusBall;

    public WinningBalls(List<LottoBall> winningBalls, int bonusBall) {
        this.bonusBall = new LottoBall(bonusBall);
        this.winningBalls = winningBalls;
    }
}
