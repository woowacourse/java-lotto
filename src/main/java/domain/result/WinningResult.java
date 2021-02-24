package domain.result;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;

public class WinningResult {
    private final Lotto winningLotto;
    private final LottoBall bonusBall;

    public WinningResult(final Lotto lotto, final LottoBall lottoBall) {
        validateWinningResult(lotto, lottoBall);
        this.winningLotto = lotto;
        this.bonusBall = lottoBall;
    }

    private void validateWinningResult(final Lotto lotto, final LottoBall lottoBall) {
        if (lotto.contains(lottoBall)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public int matchWinningLotto(final Lotto lotto) {
        return this.winningLotto.matchNumber(lotto);
    }

    public boolean matchBonusBall(final Lotto lotto) {
        return lotto.contains(this.bonusBall);
    }
}
