package domain;

import static exception.ExceptionMessage.LOTTO_NUMBER_DUPLICATED_ERROR;

import constant.WinningCount;
import exception.LottoException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, Integer bonus) {
        this.lotto = new Lotto(numbers);
        LottoNumber bonusNumber = new LottoNumber(bonus);
        validateDuplicate(this.lotto, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public void validateDuplicate(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.isDuplicateNumber(bonusNumber)) {
            throw LottoException.from(LOTTO_NUMBER_DUPLICATED_ERROR);
        }
    }

    public Map<WinningCount, Integer> getLottosResult(Lottos lottos) {
        List<Lotto> lottoList = lottos.getLottos();
        Map<WinningCount, Integer> result = new EnumMap<>(WinningCount.class);
        lottoList.stream().forEach(lotto -> {
            WinningCount lottoResult = getLottoResult(lotto);
            result.put(lottoResult, result.getOrDefault(lottoResult, 0) + 1);
        });
        return result;
    }

    private WinningCount getLottoResult(Lotto lotto) {
        List<Integer> sortedLottoNumbers = lotto.getSortedLottoNumbers();
        int matchedCount = (int) sortedLottoNumbers.stream().filter(this.lotto.getSortedLottoNumbers()::contains)
                .count();
        boolean isBonusContained = sortedLottoNumbers.contains(bonusNumber.getNumber());
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
