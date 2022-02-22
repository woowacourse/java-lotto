package lotto.model;

import java.util.List;

public class LottoMatcher {
    private final List<Integer> winningNumbers;
    private final Integer bonusNumber;

    public LottoMatcher(List<Integer> winningNumbers, Integer bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public ResultMap getWinningResult(List<Lotto> lottos) {
        ResultMap resultMap = new ResultMap();
        lottos.forEach(lotto -> {
            resultMap.getResult().put(match(lotto), resultMap.getResult().get(match(lotto)) + 1);
        });
        return resultMap;
    }

    public int matchWinningNumbers(Lotto lotto) {
        return lotto.match(winningNumbers);
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.matchBonusNumber(bonusNumber);
    }

    public Rank match(Lotto lotto) {
        return Rank.parse(matchWinningNumbers(lotto), matchBonus(lotto));
    }
}
