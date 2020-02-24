package lotto.domain;

public class Result {
    private WinningInfo winningInfo;

    public Result() {
    }

    public Result(WinningInfo winningInfo) {
        this.winningInfo = winningInfo;
    }

    void calculate(final WinningLottoTicket winningLotto, final LottoTicket userLottoTicket) {
        boolean hasBonus = winningLotto.isBonusMatched(userLottoTicket);
        int winningCount = Math.toIntExact(winningLotto.countMatchedNumber(winningLotto, userLottoTicket));

        winningInfo = WinningInfo.valueOf(winningCount, hasBonus);
    }

    public WinningInfo getWinningInfo() {
        return winningInfo;
    }
}
