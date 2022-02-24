package lotto.domain;

import static lotto.domain.BallType.BONUS;
import static lotto.domain.BallType.NORMAL;

import java.util.Objects;

public class WinningNumber {

    private static final String WINNING_NUMBER_RANGE_ERROR_MESSAGE = "당첨 번호의 범위는 1 ~ 45 사이입니다.";
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;

    private final int winningNumber;
    private final BallType ballType;

    public WinningNumber(int winningNumber, BallType ballType) {
        validateNumberRange(winningNumber);
        this.winningNumber = winningNumber;
        this.ballType = ballType;
    }

    public WinningNumber(int winningNumber) {
        this(winningNumber, NORMAL);
    }

    private void validateNumberRange(int winningNumber) {
        if (winningNumber < START_NUMBER || winningNumber > END_NUMBER) {
            throw new IllegalArgumentException(WINNING_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    public boolean isBonus() {
        return ballType == BONUS;
    }

    public int getWinningNumber() {
        return winningNumber;
    }
}
