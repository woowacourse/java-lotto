package domain;

import generator.RandomGenerator;
import java.util.List;

public class Lotto {

    private List<Integer> numbers;

    // 전략을 생성자에서 주입받음
    public Lotto(RandomGenerator randomGenerator) {
        this.numbers = randomGenerator.generateNumbers();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public LottoMatch compareLotto(WinningLotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getWinningNumbers();
        int bonusNumber = winningLotto.getBonusNumber();
        int winningCounter = 0;
        boolean bonusChecker = false;

        for (int number : numbers) {
            winningCounter += addWinningCounter(winningNumbers, number);
        }
        if (numbers.contains(bonusNumber)) {
            bonusChecker = true;
        }
        return LottoMatch.calculateLotto(winningCounter, bonusChecker);
    }

    private int addWinningCounter(List<Integer> winningNumbers, int number) {
        if (winningNumbers.contains(number)) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
