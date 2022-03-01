package lotto.domain.ticket.validation;

import lotto.domain.ticket.Ball;
import lotto.domain.ticket.Ticket;
import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

public class WinningTicketValidator {

    private WinningTicketValidator() {
    }

    public static void validateWinningTicket(final Ticket ticket, final Ball bonusBall) {
        verifyBonusBallNotDuplicated(ticket, bonusBall);
    }

    private static void verifyBonusBallNotDuplicated(final Ticket ticket, final Ball bonusBall) {
        if (ticket.contains(bonusBall)) {
            throw new LottoException(LottoExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED);
        }
    }

}
