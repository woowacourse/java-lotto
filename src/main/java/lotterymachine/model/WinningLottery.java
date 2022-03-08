package lotterymachine.model;

import java.util.List;
import lotterymachine.vo.Ball;

public class WinningLottery {
    private static final String DUPLICATE_BONUS_NUMBER_EXCEPTION = "보너스 볼이 당첨 번호와 중복됩니다.";

    private final LotteryTicket winningTicket;
    private final Ball bonusBall;

    public WinningLottery(List<Integer> numbers, int bonusBall) {
        validateBonusNumber(numbers, bonusBall);
        this.winningTicket = LotteryTicket.from(numbers);
        this.bonusBall = Ball.from(bonusBall);
    }

    private void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_EXCEPTION);
        }
    }

    public LotteryTicket getWinningTicket() {
        return winningTicket;
    }

    public Ball getBonusBall() {
        return bonusBall;
    }
}
