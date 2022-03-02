package lotterymachine.domain;

import java.util.List;

public class WinningLottery {
    private static final String NOT_CORRECT_WINNING_NUMBERS = "당첨 번호 숫자는 여섯개를 입력해야합니다.";
    private static final String DUPLICATION_INPUT_NUMBERS = "중복된 숫자를 입력 받았습니다.";
    private static final String DUPLICATION_INPUT_BONUS_NUMBERS = "보너스 볼이 당첨 번호와 중복됩니다.";

    private final List<LotteryNumber> numbers;
    private final LotteryNumber bonusNumber;

    public WinningLottery(List<LotteryNumber> numbers, LotteryNumber bonusNumber) {
        validateWinningLotteryNumbers(numbers);
        validateBonusNumber(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(List<LotteryNumber> numbers, LotteryNumber bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATION_INPUT_BONUS_NUMBERS);
        }
    }

    private void validateWinningLotteryNumbers(List<LotteryNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<LotteryNumber> numbers) {
        if (!isLotteryTicketSize(numbers.size())) {
            throw new IllegalArgumentException(NOT_CORRECT_WINNING_NUMBERS);
        }
    }

    private static boolean isLotteryTicketSize(int size) {
        return size == LotteryTicket.TICKET_SIZE;
    }

    private static void validateDuplication(List<LotteryNumber> input) {
        int numbers = (int) input.stream()
                .distinct()
                .count();
        if (numbers != input.size()) {
            throw new IllegalArgumentException(DUPLICATION_INPUT_NUMBERS);
        }
    }

    public WinningLotteryRank getWinningLotteryRank(LotteryTicket lotteryTicket) {
        int matchingNumbers = lotteryTicket.countMatchingNumbers(this.numbers);
        boolean containsBonus = lotteryTicket.containsNumber(this.bonusNumber);
        return WinningLotteryRank.find(matchingNumbers, containsBonus);
    }
}
