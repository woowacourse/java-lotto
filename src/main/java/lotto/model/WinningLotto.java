package lotto.model;

import java.util.List;

public class WinningLotto {

    private static final String LOTTO_ERROR_MESSAGE = "[ERROR] 잘못된 로또 번호입니다.";

    private final List<Integer> winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, BonusNumber bonusNumber) {
        validateWinningNumbers(winningNumbers);
        bonusNumber.hasDuplicateNumber(winningNumbers);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningNumbers(List<Integer> numbers) {
        if (!isValidLength(numbers) || !isValidRange(numbers) || isDuplicate(numbers)) {
            throw new RuntimeException(LOTTO_ERROR_MESSAGE);
        }
    }

    private boolean isValidLength(List<Integer> numbers) {
        return numbers.size() == 6;
    }

    private boolean isValidRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                return false;
            }
        }
        return true;
    }

    private boolean isDuplicate(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }


    public void checkRank(Lottos lottos) {
        lottos.calculateRanks(winningNumbers, bonusNumber);
    }
}
