package lotto.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lotto.domain.credit.Credit;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.Ball;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.dto.AnalysisDto;

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
		final List<Rank> ranks = tickets.getRanks(new Ticket(answerNumbers), new Ball(bonusBall));
		return new AnalysisDto(ranks, credit.getMoney());
	}

	public Tickets getTickets() {
		return this.tickets;
	}

}
