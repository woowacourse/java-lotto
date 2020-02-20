package lotto.domain;

import java.util.Collections;
import java.util.List;

public class WinningBalls {
    private final List<LottoBall> winningBalls;
    private final LottoBall bonusBall;

    public WinningBalls(List<LottoBall> winningBalls, int bonusBall) {
        this.bonusBall = new LottoBall(bonusBall);
        this.winningBalls = winningBalls;
    }

//    public List<LottoBall> getWinningBalls() {
//        return Collections.unmodifiableList(winningBalls);
//    }


    public int hitLottoBalls(LottoTicket lottoTicket) {
        return (int) lottoTicket.getLottoTicket()
                .stream()
                .filter(winningBalls::contains)
                .count();
    }

    public boolean hitBonus(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket().contains(bonusBall);
    }

}
