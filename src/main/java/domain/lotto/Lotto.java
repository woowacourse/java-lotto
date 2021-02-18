package domain.lotto;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.result.LottoRank;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final LottoBalls lottoBalls;

    public Lotto(final LottoBalls lottoBalls) {
        this.lottoBalls = lottoBalls;
    }

    public List<LottoBall> findLottoNumbers() {
        List<LottoBall> lottoBalls = this.lottoBalls.getLottoNumbers();
        return Collections.unmodifiableList(lottoBalls);
    }

    public LottoRank findMatchesNumber(WinningLotto winningLotto) {
        return winningLotto.winningMatchCount(lottoBalls);
    }
}
