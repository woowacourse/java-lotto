package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_NUMBERS_COUNT = 6;
    private static final int WINNING_NUMBERS_COUNT = 6;
    private static final int FIVE_MATCH = 5;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public MatchResult findMatchResult(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int sameNumberCount = calculateSameNumberCountWith(winningNumbers);
        if (sameNumberCount == FIVE_MATCH && bonusNumber.isIncluded(numbers)) {
            return MatchResult.FIVE_MATCH_WITH_BONUS_BALL;
        }
        return MatchResult.of(sameNumberCount);
    }

    private int calculateSameNumberCountWith(WinningNumbers winningNumbers) {
        Set<Integer> numbers = new HashSet<Integer>(this.numbers);
        numbers.addAll(winningNumbers.getWinningNumbers());
        int differentNumbersCount = numbers.size();
        return LOTTO_NUMBERS_COUNT + WINNING_NUMBERS_COUNT - differentNumbersCount;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
