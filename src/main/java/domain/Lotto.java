package domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public LottoMatch compareLotto(WinningLotto winningLotto) {
        int winningCounter = (int) numbers.stream()
            .filter(winningLotto::containsWinningNumber)
            .count();

        boolean bonusChecker = numbers.stream()
            .anyMatch(winningLotto::isBonusNumber);

        return LottoMatch.findLottoRank(winningCounter, bonusChecker);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
