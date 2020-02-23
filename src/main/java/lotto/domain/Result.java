package lotto.domain;

public class Result {
    private WinningInfo winningInfo;

    public Result() {
    }

    public Result(WinningInfo winningInfo) {
        this.winningInfo = winningInfo;
    }

    void calculate(WinningLotto winningLotto, Lotto userLotto) {
        boolean hasBonus = winningLotto.isBonusMatched(userLotto);
        long winningCount = winningLotto.countMatched(winningLotto, userLotto);

        winningInfo = WinningInfo.valueOf(Math.toIntExact(winningCount), hasBonus);
    }

    public WinningInfo getWinningInfo() {
        return winningInfo;
    }
}
