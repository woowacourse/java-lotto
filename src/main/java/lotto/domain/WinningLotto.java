package lotto.domain;

public class WinningLotto {
    private final WinningBalls winningBalls;
    private final BonusBall bonusBall;

    public WinningLotto(String winningBalls, String bonusBall) {
        this.winningBalls = new WinningBalls(winningBalls);
        this.bonusBall = new BonusBall(bonusBall, this.winningBalls);
    }

    public WinningBalls getWinningBalls() {
        return winningBalls;
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }
}
