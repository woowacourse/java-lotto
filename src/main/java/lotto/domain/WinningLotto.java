package lotto.domain;

public class WinningLotto {
    private final WinningBalls winningBalls;
    private final BonusBall bonusBall;

    public WinningLotto(String winningBalls, String bonusBall) {
        this.winningBalls = new WinningBalls(winningBalls);
        this.bonusBall = new BonusBall(bonusBall, this.winningBalls);
    }

    public Lotto getWinningBalls() {
        return winningBalls.getWinningBalls();
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }
}
