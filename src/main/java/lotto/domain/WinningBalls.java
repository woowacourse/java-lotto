package lotto.domain;

import java.util.List;

public class WinningBalls {
    private final List<LottoBall> winningBalls;
    private final LottoBall hitBonusBall;

    public WinningBalls(List<LottoBall> winningBalls, int hitBonusBall) {
        this.hitBonusBall = new LottoBall(hitBonusBall);
        this.winningBalls = winningBalls;
    }


    public int hitLottoBalls(LottoTicket lottoTicket) {
        return (int) lottoTicket.getLottoTicket()
                .stream()
                .filter(winningBalls::contains)
                .count();
    }

    public boolean hitBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket().contains(hitBonusBall);
    }
}
