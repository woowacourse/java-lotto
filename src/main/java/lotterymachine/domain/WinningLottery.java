package lotterymachine.domain;

import java.util.List;

import static lotterymachine.view.ErrorMessage.*;
import static lotterymachine.view.ErrorMessage.DUPLICATE_NUMBER;

public class WinningLottery {
    private static final int TICKET_SIZE = 6;

    private final List<LotteryNumber> numbers;
    private final LotteryNumber bonusNumber;

    public WinningLottery(List<LotteryNumber> numbers, LotteryNumber bonusNumber) {
        validateWinningLotteryNumbers(numbers);
        validateBonusNumber(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public List<LotteryNumber> getNumbers() {
        return numbers;
    }

    public LotteryNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateBonusNumber(List<LotteryNumber> numbers, LotteryNumber bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    private void validateWinningLotteryNumbers(List<LotteryNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<LotteryNumber> numbers) {
        if (!isLotteryTicketSize(numbers.size())) {
            throw new IllegalArgumentException(INVALID_SIZE.getMessage());
        }
    }

    public static boolean isLotteryTicketSize(int size) {
        return size == TICKET_SIZE;
    }

    private static void validateDuplication(List<LotteryNumber> input) {
        int numbers = (int) input.stream()
                .distinct()
                .count();
        if (numbers != input.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER.getMessage());
        }
    }
}
