package lotto.domain.result.win;

import lotto.domain.result.LottoResult;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoBall;

import java.util.Objects;
import java.util.Set;

public class WinningLotto {
    private final WinningBalls winningBalls;
    private final LottoBall bonusBall;

    public WinningLotto(Set<LottoBall> winningLottoBalls, LottoBall bonusBall) {
        this.winningBalls = new WinningBalls(winningLottoBalls, bonusBall);
        this.bonusBall = bonusBall;
    }

    public LottoResult createLottoResult(LottoTicket lottoTicket) {
        int numberOfMatch = winningBalls.countMatchNumber(lottoTicket);
        boolean isBonusMatch = lottoTicket.has(bonusBall);

        return new LottoResult(numberOfMatch, isBonusMatch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningBalls, that.winningBalls) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningBalls, bonusBall);
    }
}
