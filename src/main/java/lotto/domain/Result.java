package lotto.domain;

public class Result {
    private WinningInfo winningInfo;

    public Result() {
    }

    public Result(WinningInfo winningInfo) {
        this.winningInfo = winningInfo;
    }

    void calculate(WinningLottoTicket winningLotto, LottoTicket userLottoTicket) {
        boolean hasBonus = winningLotto.isBonusMatched(userLottoTicket);
        long winningCount = winningLotto.countMatched(winningLotto, userLottoTicket);

        winningInfo = WinningInfo.valueOf(Math.toIntExact(winningCount), hasBonus);
    }

    public WinningInfo getWinningInfo() {
        return winningInfo;
    }
}
