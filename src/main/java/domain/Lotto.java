package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private static final String LOTTO_NUMBER_NULL_ERROR = "[ERROR] 로또 번호로 null 값이 올 수 없습니다.";
    private static final String LOTTO_NUMBER_RANGE_ERROR = "[ERROR] 로또 번호는 1~45 사이 정수만 가능합니다.";
    private static final String LOTTO_SIZE_ERROR = "[ERROR] 로또 번호는 6개만 입력 가능합니다.";
    private static final String LOTTO_NUMBER_NOT_UNIQUE_ERROR = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    private static final int WINNING_COUNT_LIMIT = 3;
    private static final int SECOND_PRIZE_CONDITION = 5;
    private static final int NO_MEANING_COUNT = 0;

    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        checkValidate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    public List<Integer> getWinningAndBonusCount(WinningNumbers winningNumbers) {
        int winningCount = countWinningNumbers(winningNumbers);
        int bonusCount = countBonusNumber(winningNumbers);

        winningCount = checkNoReward(winningCount);
        bonusCount = checkSecondPrize(winningCount, bonusCount);

        return new ArrayList<>(List.of(winningCount, bonusCount));
    }

    private int countWinningNumbers(WinningNumbers winningNumbers) {
        return (int) lottoNumbers.stream()
            .filter(winningNumbers::isContain)
            .count();
    }

    private int countBonusNumber(WinningNumbers winningNumbers) {
        return (int) lottoNumbers.stream()
            .filter(winningNumbers::isEqualToBonusNumber)
            .count();
    }

    private int checkNoReward(int winningCount) {
        if (winningCount < WINNING_COUNT_LIMIT) {
            return NO_MEANING_COUNT;
        }
        return winningCount;
    }

    private int checkSecondPrize(int winningCount, int bonusCount) {
        if (winningCount != SECOND_PRIZE_CONDITION) {
            return NO_MEANING_COUNT;
        }
        return bonusCount;
    }


    private void checkValidate(List<Integer> numbers) {
        checkNull(numbers);
        checkRange(numbers);
        checkSize(numbers);
        checkUnique(numbers);
    }

    private void checkNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NULL_ERROR);
        }
    }

    private void checkRange(List<Integer> numbers) {
        numbers.stream()
            .filter(number -> number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER)
            .findAny()
            .ifPresent(m -> {
                throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR);
            });
    }

    private void checkSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR);
        }
    }

    private void checkUnique(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NOT_UNIQUE_ERROR);
        }
    }
}
