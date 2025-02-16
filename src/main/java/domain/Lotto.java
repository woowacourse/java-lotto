package domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public LottoMatch compareLotto(WinningLotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getWinningNumbers();
        int bonusNumber = winningLotto.getBonusNumber();
        int winningCounter = 0;
        boolean bonusChecker = false;

        winningCounter = (int) numbers.stream()
            .filter(winningNumbers::contains)
            .count();

        if (numbers.contains(bonusNumber)) {
            bonusChecker = true;
        }
        return LottoMatch.findLottoRank(winningCounter, bonusChecker);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
