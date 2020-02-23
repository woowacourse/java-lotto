package lotto.domain.result.win;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoBall;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WinningBalls {
    private final Set<LottoBall> winningBalls;

    public WinningBalls(Set<LottoBall> winningBalls, LottoBall bonusBall) {
        this.winningBalls = new HashSet<>(winningBalls);
        this.winningBalls.add(bonusBall);
    }

    public int countMatchNumber(LottoTicket lottoTicket) {
        return (int) this.winningBalls.stream()
                .filter(lottoTicket::has)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningBalls that = (WinningBalls) o;
        return Objects.equals(winningBalls, that.winningBalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningBalls);
    }
}
