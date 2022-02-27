package lotto.domain.ticket.validation;

import lotto.domain.ticket.Ball;
import lotto.domain.ticket.Ticket;
import lotto.exception.LottoException;
import lotto.exception.ticket.TicketNumbersExceptionStatus;

public class WinningTicketValidator {

    private static final WinningTicketValidator INSTANCE = new WinningTicketValidator();

    private WinningTicketValidator() {
    }

    public static void validateWinningTicket(final Ticket ticket, final Ball bonusBall) {
        INSTANCE.verifyBonusBallNotDuplicated(ticket, bonusBall);
    }

    private void verifyBonusBallNotDuplicated(final Ticket ticket, final Ball bonusBall) {
        if (ticket.contains(bonusBall)) {
            throw new LottoException(TicketNumbersExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED);
        }
    }

}
