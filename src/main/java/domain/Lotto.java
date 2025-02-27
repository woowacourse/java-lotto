package domain;

import generator.RandomGenerator;
import java.util.List;

public class Lotto {

    private List<Integer> numbers;

    public Lotto(RandomGenerator randomGenerator) {
        this.numbers = randomGenerator.generateNumbers();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
/*
    public LottoMatch compareLotto(WinningLotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getWinningNumbers();
        int bonusNumber = winningLotto.getBonusNumber();

        int winningCounter = numbers.stream()
            .mapToInt(number -> addWinningCounter(winningNumbers, number))
            .sum();

        boolean bonusChecker = numbers.contains(bonusNumber);

        return LottoMatch.calculateLotto(winningCounter, bonusChecker);
    }


    private int addWinningCounter(List<Integer> winningNumbers, int number) {
        if (winningNumbers.contains(number)) {
            return 1;
        }
        return 0;
    }*/

    @Override
    public String toString() {
        return numbers.toString();
    }

    public int calculateMatchCount(List<Integer> numbers) {
        return Math.toIntExact(numbers.stream()
            .filter((number) -> this.numbers.contains(number))
            .count());
    }

    public boolean cotainsNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}
