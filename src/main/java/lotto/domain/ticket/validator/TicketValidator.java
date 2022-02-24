package lotto.domain.ticket.validator;

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
        INSTANCE.validateTicketIsNull(ballNumbers);
        INSTANCE.validateTicketIsOutOfSize(ballNumbers);
        INSTANCE.validateNumbersDuplicated(ballNumbers);
    }

    private void validateTicketIsNull(final List<Integer> ballNumbers) {
        if (Objects.isNull(ballNumbers)) {
            throw new LottoException(TicketNumbersExceptionStatus.NUMBERS_IS_NULL);
        }
    }

    private void validateTicketIsOutOfSize(final List<Integer> ballNumbers) {
        if (TicketSize.DEFAULT_SIZE.doesNotMatch(ballNumbers.size())) {
            throw new LottoException(TicketNumbersExceptionStatus.NUMBERS_OUT_OF_SIZE);
        }
    }

    private void validateNumbersDuplicated(final List<Integer> ballNumbers) {
        if (isBallNumberDuplicated(ballNumbers)) {
            throw new LottoException(TicketNumbersExceptionStatus.NUMBERS_DUPLICATED);
        }
    }

    private boolean isBallNumberDuplicated(final List<Integer> ballNumbers) {
        return ballNumbers.stream()
                .anyMatch(ballNumber -> BallNumberDuplication.isExcessiveDuplicated(ballNumbers, ballNumber));
    }

}
