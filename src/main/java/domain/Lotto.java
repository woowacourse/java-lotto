package domain;

import dto.LottoMatchResult;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final int NUMBER_COUNT = 6;

    private final Numbers numbers;

    public Lotto(Numbers numbers) {
        this.numbers = numbers;
    }

    public LottoMatchResult getMatchResult(List<Integer> matchNumbers, int bonusNumber) {
        validateMatchNumbersNotDuplicated(matchNumbers);
        validateMatchNumbersSize(matchNumbers);
        validateBonusNumberNotDuplicated(matchNumbers, bonusNumber);

        int matchCount = 0;
        matchCount = calculateMatchNumberCount(matchNumbers, matchCount);

        return new LottoMatchResult(matchCount, numbers.isContains(bonusNumber));
    }

    private void validateMatchNumbersNotDuplicated(List<Integer> matchNumbers) {
        if (matchNumbers.size() != new HashSet<>(matchNumbers).size()) {
            throw new IllegalArgumentException("로또 당첨 번호는 중복되면 안됩니다.");
        }
    }

    private void validateMatchNumbersSize(List<Integer> matchNumbers) {
        if (matchNumbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateBonusNumberNotDuplicated(List<Integer> matchNumbers, int bonusNumber) {
        if (matchNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 중복되면 안됩니다.");
        }
    }

    private int calculateMatchNumberCount(List<Integer> matchNumbers, int matchCount) {
        for (Integer matchNumber : matchNumbers) {
            matchCount = increaseCount(matchCount, matchNumber);
        }
        return matchCount;
    }

    private int increaseCount(int matchCount, Integer matchNumber) {
        if (numbers.isContains(matchNumber)) {
            matchCount++;
        }
        return matchCount;
    }

    public Numbers getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
