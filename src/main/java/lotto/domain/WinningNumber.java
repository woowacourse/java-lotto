package lotto.domain;

import static lotto.domain.BallType.BONUS;

import java.util.List;

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

    public boolean isSame(LottoTicket lottoTicket) {
        List<Integer> lottoNumbers = lottoTicket.getLottoNumbers();

        return lottoNumbers.contains(winningNumber);
    }
}
