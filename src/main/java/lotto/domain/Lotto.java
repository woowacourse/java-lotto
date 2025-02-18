package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public MatchResultDto deriveMatchResult(final WinningNumber winningNumber,
                                            final BonusNumber bonusNumber) {
        return new MatchResultDto(countWinningNumber(winningNumber), containBonusNumber(bonusNumber));
    }

    public boolean containBonusNumber(final BonusNumber bonusNumber) {
        return bonusNumber.isMatch(numbers);
    }

    public int countWinningNumber(final WinningNumber winningNumber) {
        return winningNumber.countMatchingNumber(numbers);
    }
}
