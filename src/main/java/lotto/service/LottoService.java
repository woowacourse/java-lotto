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

	public AnalysisDto generateAnalysis(final List<Integer> answerNumbers, final int bonusBall) {
		final List<Rank> ranks = tickets.calculateRanks(new Ticket(answerNumbers), new Ball(bonusBall));
		return new AnalysisDto(ranks, credit.getMoney());
	}

	public List<TicketDto> getTicketDtos() {
		return tickets.getTicketDtos();
	}

}
