package lotto.domain.ticket.validation;

import java.util.List;
import java.util.Objects;

import lotto.domain.ticket.condition.BallNumberDuplication;
import lotto.domain.ticket.condition.TicketSize;
import lotto.exception.LottoException;
import lotto.exception.ticket.TicketNumbersExceptionStatus;

public class TicketValidator {

    private static final TicketValidator INSTANCE = new TicketValidator();

    private TicketValidator() {
    }

    public static void validateTicket(final List<Integer> ballNumbers) {
        INSTANCE.verifyNumbersNotNull(ballNumbers);
        INSTANCE.verifyNumbersNotOutOfSize(ballNumbers);
        INSTANCE.verifyNumbersNotDuplicated(ballNumbers);
    }

    private void verifyNumbersNotNull(final List<Integer> ballNumbers) {
        if (Objects.isNull(ballNumbers)) {
            throw new LottoException(TicketNumbersExceptionStatus.TICKET_NUMBERS_CANNOT_BE_NULL);
        }
    }

    private void verifyNumbersNotOutOfSize(final List<Integer> ballNumbers) {
        if (TicketSize.DEFAULT_SIZE.doesNotMatch(ballNumbers.size())) {
            throw new LottoException(TicketNumbersExceptionStatus.TICKET_NUMBERS_CANNOT_BE_OUT_OF_SIZE);
        }
    }

    private void verifyNumbersNotDuplicated(final List<Integer> ballNumbers) {
        if (isNumberDuplicated(ballNumbers)) {
            throw new LottoException(TicketNumbersExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED);
        }
    }

    private boolean isNumberDuplicated(final List<Integer> ballNumbers) {
        return ballNumbers.stream()
                .anyMatch(ballNumber -> BallNumberDuplication.isExcessiveDuplicated(ballNumbers, ballNumber));
    }

}
