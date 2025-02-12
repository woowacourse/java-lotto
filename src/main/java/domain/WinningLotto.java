package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    private final Lotto lotto;
    private final Number bonusNumber;

    public WinningLotto(Lotto lotto, Number bonusNumber) {
        validateDuplicatedNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatedNumber(Lotto lotto, Number bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Map<Winning, Integer> calculateWinning(List<Lotto> purchaseLottos) {
        Map<Winning, Integer> winningResult = new HashMap<>();
        for (Lotto purchaseLotto : purchaseLottos) {
            int matchCount = lotto.calculateMatchCount(purchaseLotto);
            boolean isMatchBonusNumber = purchaseLotto.contains(bonusNumber);
            Winning winning = Winning.findWinning(matchCount, isMatchBonusNumber);
            winningResult.put(winning, winningResult.getOrDefault(winning, 0) + 1);
        }

        return winningResult;
    }
}
