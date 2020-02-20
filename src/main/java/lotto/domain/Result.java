package lotto.domain;

public class Result {
    private WinningInfo winningInfo;

    public Result() {
    }

    public Result(WinningInfo winningInfo) {
        this.winningInfo = winningInfo;
    }

    void calculate(WinningLotto winningLotto, Lotto userLotto) {
        boolean hasBonus = userLotto.getLottoNumbers().stream()
                .anyMatch(x -> winningLotto.getBonusNumber().equals(x));
        long winningCount = userLotto.getLottoNumbers().stream()
                .filter(x -> winningLotto.getLottoNumbers().contains(x))
                .count();
        winningInfo = WinningInfo.valueOf(Math.toIntExact(winningCount), hasBonus);
    }

    public WinningInfo getWinningInfo() {
        return winningInfo;
    }
}
