package lotto.domain;

import java.util.Objects;
import java.util.Set;

public class WinningLotto {
    private final WinningBalls winningBalls;
    private final LottoBall bonusBall;

    public WinningLotto(Set<LottoBall> lottoBalls, LottoBall bonusBall) {
        this.winningBalls = new WinningBalls(lottoBalls, bonusBall);
        this.bonusBall = bonusBall;
    }

    public LottoResult getResult(LottoTicket buyLottoTicket) {
        int matchCount = winningBalls.getMatchCount(buyLottoTicket);
        boolean isBonusMatch = buyLottoTicket.has(bonusBall);

        return new LottoResult(matchCount, isBonusMatch);
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
