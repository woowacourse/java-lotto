package lotto.domain.ticket.validator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TicketValidator {

    private static final int TICKET_DEFAULT_SIZE = 6;
    private static final String TICKET_NULL_POINTER_EXCEPTION_MESSAGE = "숫자 요소는 NULL이 아니어야 합니다.";
    private static final String TICKET_OUT_OF_SIZE_EXCEPTION_MESSAGE = "숫자 요소는 6개여야 합니다.";
    private static final String TICKET_NUMBERS_DUPLICATED_EXCEPTION_MESSAGE = "숫자 요소는 중복될 수 없습니다.";

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
            throw new IllegalArgumentException(TICKET_NULL_POINTER_EXCEPTION_MESSAGE);
        }
    }

    private void validateTicketIsOutOfSize(final List<Integer> numbers) {
        if (numbers.size() != TICKET_DEFAULT_SIZE) {
            throw new IllegalArgumentException(TICKET_OUT_OF_SIZE_EXCEPTION_MESSAGE);
        }
    }

    private void validateNumbersDuplicated(final List<Integer> numbers) {
        if (isNumberDuplicated(numbers)) {
            throw new IllegalArgumentException(TICKET_NUMBERS_DUPLICATED_EXCEPTION_MESSAGE);
        }
    }

    private boolean isNumberDuplicated(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> Collections.frequency(numbers, number) > 1);
    }

}
