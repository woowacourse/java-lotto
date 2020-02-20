package lotto.domain;

import java.util.function.Predicate;

public class Result {
    private WinningInfo winningInfo;

    void calculate(Lotto userLotto, WinningLotto winningLotto) {
        int winningCount = (int) userLotto.getLottoNumbers()
                .stream()
                .filter(userLottoNumber -> winningLotto.lottoNumbers.stream().anyMatch(Predicate.isEqual(userLottoNumber)))
                .count();
        boolean hasBonus = userLotto.getLottoNumbers()
                .stream()
                .anyMatch(userLottoNumber -> userLottoNumber == winningLotto.getBonusNumber());
        this.winningInfo = WinningInfo.valueOf(winningCount, hasBonus);
    }

    public WinningInfo getWinningInfo() {
        return winningInfo;
    }

    public boolean isSameWinning(WinningInfo winningInfo) {
        return this.winningInfo == winningInfo;
    }
}
