package lotto.domain;

import lotto.Exception.DuplicationException;

import java.util.Collections;
import java.util.List;

public class WinningBalls {
    private final List<LottoBall> winningBalls;
    private final LottoBall hitBonusBall;

    public WinningBalls(List<LottoBall> winningBalls, int hitBonusBall) {
        this.winningBalls = Collections.unmodifiableList(winningBalls);
        this.hitBonusBall = LottoBallFactory.findByLottoBall(hitBonusBall);
        validateWinningBallsWithDuplicatedBonusBall();
    }

    private void validateWinningBallsWithDuplicatedBonusBall() {
        if (winningBalls.contains(this.hitBonusBall)) {
            throw new DuplicationException("보너스 볼이 중복입니다. 당첨 번호를 다시 입력해주세요.");
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
                .contains(hitBonusBall);
    }
}
