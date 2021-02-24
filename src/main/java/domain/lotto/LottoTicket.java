package domain.lotto;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.result.LottoRank;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    public static final int TICKET_PRICE = 1000;

    private final LottoBalls lottoBalls;

    public LottoTicket(final LottoBalls lottoBalls) {
        this.lottoBalls = lottoBalls;
    }

    public List<LottoBall> getLottoBalls() {
        List<LottoBall> lottoBalls = this.lottoBalls.getLottoBalls();
        return Collections.unmodifiableList(lottoBalls);
    }

    public LottoRank findLottoRankByWinningLotto(WinningLotto winningLotto) {
        return winningLotto.findLottoRank(lottoBalls);
    }
}
