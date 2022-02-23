package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoMatcher {
    public static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";
    public static final String ERROR_OUT_OF_RANGE_BONUS_NUMBER = "보너스 볼 번호가 1~45 범위 내에 해당하지 않습니다.";
    private final WinningNumbers winningNumbers;
    private final Integer bonusNumber;

    public LottoMatcher(List<Integer> winningNumbers, Integer bonusNumber) {
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        validateRangeBonusNumber(bonusNumber);
        this.winningNumbers = new WinningNumbers(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public ResultMap getWinningResult(Lottos lottos) {
        ResultMap resultMap = new ResultMap();
        Integer defaultValue = 0;
        lottos.getLottos().forEach(lotto -> {
            resultMap.getResult().put(match(lotto), resultMap.getResult().getOrDefault(match(lotto), defaultValue) + 1);
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

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    private void validateRangeBonusNumber(Integer bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE_BONUS_NUMBER);
        }
    }
}
