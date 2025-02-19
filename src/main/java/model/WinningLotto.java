package model;

import static common.LottoTicketConstant.LOTTO_MAX_NUMBER;
import static common.LottoTicketConstant.LOTTO_MIN_NUMBER;

import java.util.List;

public class WinningLotto {

    private final LottoTicket winningLottoTicket;
    private final int bonusNumber;

    public WinningLotto(LottoTicket winningLottoTicket, int bonusNumber) {
        validateBonusNumber(winningLottoTicket.getNumbers(), bonusNumber);

        this.winningLottoTicket = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public int countOverlappedNumbers(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(winningLottoTicket.getNumbers()::contains)
                .count();
    }

    public boolean isOverlappedBonusNumber(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }

    private void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        validateRange(bonusNumber);
        validateOverlap(numbers, bonusNumber);
    }

    private void validateRange(int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private void validateOverlap(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복되어서는 안됩니다.");
        }
    }
}
