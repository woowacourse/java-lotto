package lotto.dto;

import java.util.List;

import lotto.domain.ticket.Ball;
import lotto.domain.ticket.Balls;
import lotto.domain.ticket.WinningTicket;

public class WinningTicketDto {

    private final TicketDto ticketDto;
    private final int bonusNumber;

    public WinningTicketDto(final List<Integer> winningNumbers, final int bonusNumber) {
        this.ticketDto = new TicketDto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public WinningTicket toWinningTicket() {
        return new WinningTicket(ticketDto.toTicket(), Balls.getBall(bonusNumber));
    }

}
