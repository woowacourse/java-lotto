package lotto.domain.winning;

import java.util.List;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

public class WinningTicketValidator {

    private WinningTicketValidator() {
    }

    public static void validateWinningTicket(final List<Integer> numbers, final int bonusNumber) {
        verifyBonusBallNotDuplicated(numbers, bonusNumber);
    }

    private static void verifyBonusBallNotDuplicated(final List<Integer> numbers, final int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new LottoException(LottoExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED);
        }
    }

}
