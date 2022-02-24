package lotto.domain;

import static lotto.domain.enumeration.BallType.BONUS;

import lotto.domain.enumeration.BallType;

public class WinningNumber {

    private final int winningNumber;
    private final BallType ballType;

    public WinningNumber(int winningNumber, BallType ballType) {
        checkNumberBoundary(winningNumber);

        this.winningNumber = winningNumber;
        this.ballType = ballType;
    }

    public boolean isBonus() {
        return this.ballType == BONUS;
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    private void checkNumberBoundary(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("1~45의 숫자이어야 합니다.");
        }
    }
}
