package lotto.domain.ticket.validator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lotto.exception.LottoException;
import lotto.exception.ticket.TicketNumbersExceptionStatus;

public class TicketValidator {

    private static final int TICKET_DEFAULT_SIZE = 6;

    private static final TicketValidator INSTANCE = new TicketValidator();

    private TicketValidator() {
    }

    public static void validateTicket(final List<Integer> numbers) {
        INSTANCE.validateTicketIsNull(numbers);
        INSTANCE.validateTicketIsOutOfSize(numbers);
        INSTANCE.validateNumbersDuplicated(numbers);
    }

    private void validateTicketIsNull(final List<Integer> numbers) {
        if (Objects.isNull(numbers)) {
            throw new LottoException(TicketNumbersExceptionStatus.NUMBERS_IS_NULL);
        }
    }

    private void validateTicketIsOutOfSize(final List<Integer> numbers) {
        if (numbers.size() != TICKET_DEFAULT_SIZE) {
            throw new LottoException(TicketNumbersExceptionStatus.NUMBERS_OUT_OF_SIZE);
        }
    }

    private void validateNumbersDuplicated(final List<Integer> numbers) {
        if (isNumberDuplicated(numbers)) {
            throw new LottoException(TicketNumbersExceptionStatus.NUMBERS_DUPLICATED);
        }
    }

    private boolean isNumberDuplicated(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> Collections.frequency(numbers, number) > 1);
    }

}
