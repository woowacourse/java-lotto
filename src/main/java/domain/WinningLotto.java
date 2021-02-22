package domain;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoBall bonusBall;

    public WinningLotto(final Lotto lotto, final LottoBall lottoBall) {
        validateWinningLotto(lotto, lottoBall);
        this.winningLotto = lotto;
        this.bonusBall = lottoBall;
    }

    private void validateWinningLotto(final Lotto lotto, final LottoBall lottoBall) {
        if (lotto.contains(lottoBall)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
