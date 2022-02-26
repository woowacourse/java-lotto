package lotto.domain;

import static lotto.domain.enumeration.BallType.BONUS;

import lotto.domain.enumeration.BallType;
import lotto.domain.vo.LottoNumber;

public class WinningNumber {

    private final LottoNumber winningNumber;
    private final BallType ballType;

    public WinningNumber(LottoNumber winningNumber, BallType ballType) {
        this.winningNumber = winningNumber;
        this.ballType = ballType;
    }

    public boolean isBonus() {
        return this.ballType == BONUS;
    }

    public LottoNumber getWinningNumber() {
        return winningNumber;
    }
}
