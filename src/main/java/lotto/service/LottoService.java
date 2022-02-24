package lotto.service;

import java.util.List;

import lotto.domain.credit.Credit;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.Ball;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;

public class LottoService {

    private Tickets tickets;
    private Credit credit;

    public void saveCredit(final int creditMoney) {
        this.credit = new Credit(creditMoney);
    }

    public void generateTickets() {
        final int ticketCount = credit.getQuotient();
        this.tickets = new Tickets(ticketCount, new RandomTicketGenerator());
    }

    public AnalysisDto generateAnalysis(final WinningTicketDto winningTicketDto) {
        final Ticket winningTicket = new Ticket(winningTicketDto.getWinningNumbers());
        final Ball bonusBall = new Ball(winningTicketDto.getBonusNumber());

        final List<Rank> ranks = tickets.calculateRanks(winningTicket, bonusBall);
        return new AnalysisDto(ranks, credit.getMoney());
    }

    public List<TicketDto> getTicketDtos() {
        return tickets.getTicketDtos();
    }

}
