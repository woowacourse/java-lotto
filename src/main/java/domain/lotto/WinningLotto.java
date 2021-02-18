package domain.lotto;

import domain.ball.BonusBall;
import domain.ball.LottoBalls;
import domain.result.LottoRank;

public class WinningLotto {
    private final LottoBalls lottoBalls;
    private final BonusBall bonusBall;

    public WinningLotto(final LottoBalls lottoBalls, final BonusBall bonusBall) {
        validateWinningLotto(lottoBalls, bonusBall);
        this.lottoBalls = lottoBalls;
        this.bonusBall = bonusBall;
    }

    private void validateWinningLotto(final LottoBalls lottoBalls, final BonusBall bonusBall) {
        if (lottoBalls.containNumber(bonusBall)) {
            throw new IllegalArgumentException("중복된 값이 있습니다. 다시 입력해주세요 ");
        }
    }

    public LottoRank winningMatchCount(LottoBalls lottoBalls) {
        return lottoBalls.matchCount(this.lottoBalls, bonusBall);
    }
}
