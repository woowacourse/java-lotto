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

    private List<LottoNumber> lottoNumbers;

    public Lotto() {
        this.lottoNumbers = generateNumber();
    }

    public static Lotto generateLotto() {
        return new Lotto();
    }

    public Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return this.lottoNumbers;
    }

    public List<LottoNumber> generateNumber() {

        List<Integer> numbers = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        List<Integer> subNumbers = numbers.subList(MIN_RANGE, MAX_RANGE);
        Collections.sort(subNumbers);
        lottoNumbers = subNumbers.stream()
                .map(number -> new LottoNumber(number))
                .collect(Collectors.toList());

        return lottoNumbers;
    }

    private List<Integer> getNumbers() {
        return this.lottoNumbers
                .stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }

    public int countDuplicatedNumber(Lotto winningLotto) {
        List<Integer> numbers = getNumbers();
        return (int) winningLotto.lottoNumbers
                .stream()
                .map(LottoNumber::getNumber)
                .filter(numbers::contains)
                .count();
    }

    public boolean isBonusNumberContain(LottoNumber bonusNumber) {
        return getNumbers().contains(bonusNumber.getNumber());
    }

    public Rewards checkWinning(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {

        int winningCount = countWinningNumbers(winningNumbers);
        int bonusCount = countBonusNumber(bonusNumber);

        winningCount = checkNoReward(winningCount);
        bonusCount = checkSecondPrize(winningCount, bonusCount);

        return Rewards.findReward(winningCount, bonusCount);
    }

    private int countWinningNumbers(List<LottoNumber> winningNumbers) {
        return (int) lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .filter(winningNumbers::contains)
                .count();
    }

    private int countBonusNumber(LottoNumber bonusNumber) {
        return (int) lottoNumbers.stream()
                .map(LottoNumber::getNumber)
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
