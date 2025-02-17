package domain;

import java.util.List;

public class WinningResultEvaluator {
    private final WinningNumber winningNumber;
    private final BonusNumber bonusNumber;

    public WinningResultEvaluator(WinningNumber winningNumber, BonusNumber bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public WinningResult evaluate(List<Lotto> issuedLottos) {
        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : issuedLottos) {
            int matchingCount = winningNumber.findMatchingCountWith(lotto.getNumbers());
            boolean isMatchedWithBonusNumber = bonusNumber.matchesWith(lotto.getNumbers());
            WinningStatus winningStatus = WinningStatus.findBy(matchingCount, isMatchedWithBonusNumber);
            winningResult.update(winningStatus);
        }
        return winningResult;
    }
}
