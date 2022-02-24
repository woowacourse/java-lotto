package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 46;

    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 6;

    private static final int WINNING_COUNT_LIMIT = 3;
    private static final int SECOND_PRIZE_CONDITION = 5;
    private static final int NO_MEANING_COUNT = 0;

    private List<Integer> lottoNumbers;

    public Lotto() {
        this.lottoNumbers = generateNumber();
    }

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }

    public List<Integer> generateNumber() {

        List<Integer> numbers = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        lottoNumbers = numbers.subList(MIN_RANGE, MAX_RANGE);
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }

    public Rewards checkWinning(List<Integer> winningNumbers, Integer bonusNumber) {

        int winningCount = countWinningNumbers(winningNumbers);
        int bonusCount = countBonusNumber(bonusNumber);

        winningCount = checkNoReward(winningCount);
        bonusCount = checkSecondPrize(winningCount, bonusCount);

        return Rewards.findReward(winningCount, bonusCount);
    }

    private int countWinningNumbers(List<Integer> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private int countBonusNumber(Integer bonusNumber) {
        return (int) lottoNumbers.stream()
                .filter(bonusNumber::equals)
                .count();
    }

    private int checkNoReward(Integer winningCount) {
        if (winningCount < WINNING_COUNT_LIMIT) {
            return NO_MEANING_COUNT;
        }
        return winningCount;
    }

    private int checkSecondPrize(Integer winningCount, Integer bonusCount) {
        if (winningCount != SECOND_PRIZE_CONDITION) {
            return NO_MEANING_COUNT;
        }
        return bonusCount;
    }
}
