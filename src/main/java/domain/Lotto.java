package domain;

import view.InputView;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        InputView.validateWinningNumbers(numbers);
        this.numbers = numbers;
    }

    public String toString() {
        return numbers.toString();
    }

    public Rank getRank(WinningLotto winnigLotto) {
        int matchCount = (int) numbers.stream()
                .filter(winnigLotto::containsNumber)
                .count();
        return Rank.fromResult(matchCount, winnigLotto.hasBonusBall(numbers));
    }
}
