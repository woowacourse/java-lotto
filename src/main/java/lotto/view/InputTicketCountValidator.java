package lotto.view;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

public class InputTicketCountValidator {

    private InputTicketCountValidator() {
    }

    public static void verifyTicketCount(final int totalTicketCount, final int ticketCount) {
        verifyTicketCountPositive(ticketCount);
        verifyTotalTicketCountIsMoreThanTicketCount(totalTicketCount, ticketCount);
    }

    private static void verifyTicketCountPositive(final int ticketCount) {
        if (ticketCount <= 0) {
            throw new LottoException(LottoExceptionStatus.MANUAL_TICKET_COUNT_MUST_BE_POSITIVE);
        }
    }

    private static void verifyTotalTicketCountIsMoreThanTicketCount(final int totalTicketCount, final int ticketCount) {
        if (ticketCount > totalTicketCount) {
            throw new LottoException(LottoExceptionStatus.MANUAL_TICKET_COUNT_CANNOT_BE_TOO_MANY);
        }
    }

}
