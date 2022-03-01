package lotto.domain.ticket.validation;

import java.util.List;

import lotto.domain.ticket.condition.BallNumberDuplication;
import lotto.domain.ticket.condition.TicketSize;
import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

public class TicketValidator {

    private TicketValidator() {
    }

    public static void validateTicket(final List<Integer> ballNumbers) {
        verifyNumbersNotOutOfSize(ballNumbers);
        verifyNumbersNotDuplicated(ballNumbers);
    }

    private static void verifyNumbersNotOutOfSize(final List<Integer> ballNumbers) {
        if (TicketSize.DEFAULT_SIZE.doesNotMatch(ballNumbers.size())) {
            throw new LottoException(LottoExceptionStatus.TICKET_NUMBERS_CANNOT_BE_OUT_OF_SIZE);
        }
    }

    private static void verifyNumbersNotDuplicated(final List<Integer> ballNumbers) {
        if (isNumberDuplicated(ballNumbers)) {
            throw new LottoException(LottoExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED);
        }
    }

    private static boolean isNumberDuplicated(final List<Integer> ballNumbers) {
        return ballNumbers.stream()
                .anyMatch(ballNumber -> BallNumberDuplication.isExcessiveDuplicated(ballNumbers, ballNumber));
    }

}
