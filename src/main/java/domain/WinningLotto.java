package domain;

import static exception.ExceptionMessage.LOTTO_NUMBER_DUPLICATED_ERROR;
import static exception.ExceptionMessage.LOTTO_RANGE_ERROR;

import constant.WinningCount;
import dto.WinningLottoDto;
import exception.LottoException;
import java.util.List;

public class WinningLotto {
    private final Lotto lotto;
    private final Integer bonusNumber;

    public WinningLotto(List<Integer> numbers, Integer bonusNumber) {
        this.lotto = new Lotto(numbers);
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public void validate(Integer bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateDuplicate(bonusNumber);
    }

    private void validateBonusNumberRange(Integer bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw LottoException.from(LOTTO_RANGE_ERROR);
        }
    }

    private void validateDuplicate(Integer bonusNumber) {
        List<Integer> lottos = this.lotto.getSortedNumbers();
        if (lottos.contains(bonusNumber)) {
            throw LottoException.from(LOTTO_NUMBER_DUPLICATED_ERROR);
        }
    }

    public WinningLottoDto getWinningLottoDto() {
        return new WinningLottoDto(lotto.getSortedNumbers(), bonusNumber);
    }

    public WinningCount getLottoResult(List<Integer> issuedLotto) {
        int matchedCount = (int) issuedLotto.stream().filter(lotto.getSortedNumbers()::contains).count();
        boolean isBonusContained = issuedLotto.contains(bonusNumber);
        return getWinningCount(matchedCount, isBonusContained);
    }

    private WinningCount getWinningCount(int matchedCount, boolean isBonusContained) {
        if (matchedCount < WinningCount.THREE.getMatchedCount()) {
            return WinningCount.NONE;
        }
        if (matchedCount == WinningCount.THREE.getMatchedCount()) {
            return WinningCount.THREE;
        }
        if (matchedCount == WinningCount.FOUR.getMatchedCount()) {
            return WinningCount.FOUR;
        }
        if (matchedCount == WinningCount.FIVE.getMatchedCount() && isBonusContained) {
            return WinningCount.FIVE_BONUS;
        }
        if (matchedCount == WinningCount.FIVE.getMatchedCount()) {
            return WinningCount.FIVE;
        }
        return WinningCount.SIX;
    }
}
