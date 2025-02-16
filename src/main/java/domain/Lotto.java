package domain;

import view.InputView;
import view.Validator;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        Validator.validateWinningNumbers(numbers);
        this.numbers = numbers;
    }

    public String toString() {
        return numbers.toString();
    }

    public Rank getRank(WinningLotto winningLotto) {
        int matchCount = (int) numbers.stream()
                .filter(winningLotto::containsNumber)
                .count();
        return Rank.fromResult(matchCount, winningLotto.hasBonusBall(numbers));
    }
}
