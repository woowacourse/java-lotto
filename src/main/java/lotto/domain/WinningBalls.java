package lotto.domain;

import lotto.Exception.DuplicationException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class WinningBalls {
    public static final String DUPLICATION_ERROR_MESSAGE = "보너스 볼이 중복입니다. 당첨 번호를 다시 입력해주세요.";
    private Set<LottoBall> winningBalls;
    private LottoBall bonusBall;

    public WinningBalls(Set<LottoBall> winningBalls, LottoBall bonusBall) {
        this.winningBalls = winningBalls;
        this.bonusBall = bonusBall;
        validateWinningBallsWithDuplicatedBonusBall();
    }

    private void validateWinningBallsWithDuplicatedBonusBall() {
        if (winningBalls.contains(this.bonusBall)) {
            throw new DuplicationException(DUPLICATION_ERROR_MESSAGE);
        }
    }

    public int hitLottoBalls(LottoTicket lottoTicket) {
        return (int) lottoTicket.getLottoTicket()
                .stream()
                .filter(winningBalls::contains)
                .count();
    }

    public boolean hitBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket()
                .contains(bonusBall);
    }
}