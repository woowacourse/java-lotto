package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public LottoRank checkLottoRank(WinningLotto winningLotto) {
        int winningCounter = (int) numbers.stream()
            .filter(winningLotto::containsWinningNumber)
            .count();

        boolean bonusChecker = numbers.stream()
            .anyMatch(winningLotto::isBonusNumber);

        return LottoRank.findLottoRank(winningCounter, bonusChecker);
    }
}
