package lotto.domain;

import java.util.Collections;
import java.util.List;

public class WinningBalls {
    private final List<LottoBall> winningBalls;
    private final LottoBall hitBonusBall;

    public WinningBalls(List<LottoBall> winningBalls, int hitBonusBall) {
        this.winningBalls = Collections.unmodifiableList(winningBalls);
        this.hitBonusBall = LottoBallFactory.findByLottoBall(hitBonusBall);
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
