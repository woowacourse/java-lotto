package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

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

        List<Integer> numbers = IntStream.range(1, 46)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        lottoNumbers = numbers.subList(0, 6);
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
        if (winningCount < 3) {
            return 0;
        }
        return winningCount;
    }

    private int checkSecondPrize(Integer winningCount, Integer bonusCount) {
        if (winningCount != 5) {
            return 0;
        }
        return bonusCount;
    }
}
