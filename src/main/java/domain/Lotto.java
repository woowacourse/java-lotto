package domain;

import generator.RandomGenerator;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    public static final int LOTTO_MIN_NUM = 1;
    public static final int LOTTO_MAX_NUM = 45;
    public static final int LOTTO_UNIT = 1000;

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
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
