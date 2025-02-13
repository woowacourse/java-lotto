package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public MatchResultDto deriveMatchResult(WinningNumber winningNumber, BonusNumber bonusNumber) {
        return new MatchResultDto(countWinningNumber(winningNumber), containBonusNumber(bonusNumber));
    }

    public boolean containBonusNumber(BonusNumber bonusNumber) {
        return bonusNumber.isMatch(numbers);
    }

    public int countWinningNumber(WinningNumber winningNumber) {
        return winningNumber.countMatchingNumber(numbers);
    }
}
