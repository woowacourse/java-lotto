package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final int NUMBER_RANGE_MINIMUM = 1;
    private static final int NUMBER_RANGE_MAXIMUM = 45;

    private static final String OUT_OF_RANGE_ERROR = "[ERROR] 1이상 45이하 값을 입력해주세요.";
    private static final String DUPLICATED_NUMBER_ERROR = "[ERROR] 당첨번호와 보너스볼은 중복될 수 없습니다.";

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        checkWinningAndBonusNumberValidate(winningNumbers, bonusNumber);
        this.winningNumbers = new ArrayList<>(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public boolean isContain(int number) {
        return winningNumbers.contains(number);
    }

    public boolean isEqualToBonusNumber(int number) {
        return number == bonusNumber;
    }

    private void checkWinningAndBonusNumberValidate(List<Integer> winningNumbers, int bonusNumber) {
        List<Integer> numbers = new ArrayList<>(winningNumbers);
        numbers.add(bonusNumber);
        checkNumbersRange(numbers);
        checkDuplicate(numbers);
    }

    private static void checkNumbersRange(List<Integer> numbers) {
        for (int number : numbers) {
            checkBoundary(number);
        }
    }

    private static void checkBoundary(int number) {
        if (number < NUMBER_RANGE_MINIMUM || number > NUMBER_RANGE_MAXIMUM) {
            throw new IllegalArgumentException(OUT_OF_RANGE_ERROR);
        }
    }

    private static void checkDuplicate(List<Integer> numbers) {
        Set<Integer> noDuplicatedNumbers = new HashSet<>(numbers);
        if (numbers.size() != noDuplicatedNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_ERROR);
        }
    }


}
