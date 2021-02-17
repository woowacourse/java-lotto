package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final int WINNING_TICKET_SIZE = 6;
    private static final int BONUS_NUMBER_SIZE = 1;

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    private WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        this.winningTicket = LottoTicket.valueOf(winningNumbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    public static WinningNumbers valueOf(final List<Integer> winningNumbers, final int bonusNumber) {
        validateDistinctBonus(winningNumbers, bonusNumber);
        return new WinningNumbers(winningNumbers, bonusNumber);
    }

    private static void validateDistinctBonus(final List<Integer> winningNumbers, final int bonusNumber) {
        final Set<Integer> numbers = new HashSet<>(winningNumbers);
        numbers.add(bonusNumber);

        if (numbers.size() != WINNING_TICKET_SIZE + BONUS_NUMBER_SIZE) {
            throw new IllegalArgumentException("당첨 번호에 보너스 번호가 포함되어 있습니다.");
        }
    }
}
