package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoMatcher {
    public static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";
    public static final String ERROR_OUT_OF_RANGE_BONUS_NUMBER = "보너스 볼 번호가 1~45 범위 내에 해당하지 않습니다.";
    public static final String ERROR_NOT_MATCH_WINNING_NUMBER_SIZE = "지난 주 당첨 번호 개수는 6개로 입력해주세요.";
    public static final String ERROR_OUT_OF_RANGE_WINNING_NUMBERS = "당첨 번호의 범위는 1 ~ 45 사이로 입력해주세요.";
    public static final String ERROR_DUPLICATION_WINNING_NUMBERS = "당첨 번호에 중복이 존재합니다.";
    private final List<Integer> winningNumbers;
    private final Integer bonusNumber;

    public LottoMatcher(List<Integer> winningNumbers, Integer bonusNumber) {
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        validateRangeBonusNumber(bonusNumber);
        validateNumberOfWinningNumbers(winningNumbers);
        validateRangeWinningNumbers(winningNumbers);
        validateDuplicationWinningNumbers(winningNumbers);
        this.winningNumbers = winningNumbers;
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

    private void validateNumberOfWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_NOT_MATCH_WINNING_NUMBER_SIZE);
        }
    }

    private void validateRangeWinningNumbers(List<Integer> winningNumbers) {
        winningNumbers.forEach(number -> {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(ERROR_OUT_OF_RANGE_WINNING_NUMBERS);
            }
        });
    }

    private void validateDuplicationWinningNumbers(List<Integer> winningNumbers) {
        Set<Integer> distinct = new HashSet<>(winningNumbers);
        if (distinct.size() != winningNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_WINNING_NUMBERS);
        }
    }
}
