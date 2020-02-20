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
                .anyMatch(userLottoNumber -> winningLotto.getBonusNumber().equals(userLottoNumber));
        long winningCount = userLotto.getLottoNumbers().stream()
                .filter(userLottoNumber -> winningLotto.getLottoNumbers().contains(userLottoNumber))
                .count();
        winningInfo = WinningInfo.valueOf(Math.toIntExact(winningCount), hasBonus);
    }

    public WinningInfo getWinningInfo() {
        return winningInfo;
    }
}
