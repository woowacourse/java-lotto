package lotto.domain;

import static lotto.domain.BallType.BONUS;

public class WinningNumber {

    private final int winningNumber;
    private final BallType ballType;

    public WinningNumber(int winningNumber, BallType ballType) {
        this.winningNumber = winningNumber;
        this.ballType = ballType;
    }

    public boolean isBonus() {
        return this.ballType == BONUS;
    }
}
