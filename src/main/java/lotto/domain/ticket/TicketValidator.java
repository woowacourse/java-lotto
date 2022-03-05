package lotto.domain.ticket;

import java.util.Collections;
import java.util.List;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;
import lotto.utils.TicketSize;

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
                .anyMatch(ballNumber -> Collections.frequency(ballNumbers, ballNumber) > 1);
    }

}
