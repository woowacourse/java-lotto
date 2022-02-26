package lotterymachine.domain;

import java.util.List;

import static lotterymachine.view.ErrorMessage.*;
import static lotterymachine.view.ErrorMessage.DUPLICATE_NUMBER;

public class WinningLotteryNumbers {
    private final List<Integer> numbers;
    private final int bonusNumber;

    public WinningLotteryNumbers(List<Integer> numbers, int bonusNumber) {
        validateWinningLotteryNumbers(numbers);
        validateBonusNumber(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    private void validateWinningLotteryNumbers(List<Integer> numbers) {
        validateNumbers(numbers);
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        numbers.stream()
                .filter(LotteryRule::isRangeOfNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(OUT_OF_RANGE.getMessage()));
    }

    private void validateSize(List<Integer> numbers) {
        if (!LotteryRule.isLotteryTicketSize(numbers.size())) {
            throw new IllegalArgumentException(INVALID_SIZE.getMessage());
        }
    }

    private static void validateDuplication(List<Integer> input) {
        int numbers = (int) input.stream()
                .distinct()
                .count();
        if (numbers != input.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER.getMessage());
        }
    }
}
