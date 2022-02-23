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

	public void initPayment(final int payment) {
		this.credit = new Credit(payment);
	}

	public void generateTickets() {
		final int ticketCount = credit.getQuotient();
		this.tickets = new Tickets(ticketCount, new RandomTicketGenerator());
	}

	public Tickets getTickets() {
		return this.tickets;
	}

	public AnalysisDto generateAnalysis(final List<Integer> answerNumbers, final int bonusBall) {
		final List<Rank> ranks = tickets.getRanks(new Ticket(answerNumbers), new Ball(bonusBall));
		final Map<Rank, Integer> rankCounts = getRankCount(ranks);
		final double profitRate = getProfitRate(credit.getMoney(), rankCounts);

		return new AnalysisDto(rankCounts, profitRate);
	}

	public Map<Rank, Integer> getRankCount(final List<Rank> ranks) {
		final Map<Rank, Integer> rankMap = new LinkedHashMap<>();

		for (Rank rank : Rank.values()) {
			final int count = Collections.frequency(ranks, rank);
			rankMap.put(rank, count);
		}
		return rankMap;
	}

	public double getProfitRate(final int payment, final Map<Rank, Integer> rankCounts) {
		long total = 0L;

		for (Rank rank : rankCounts.keySet()) {
			total += rank.getPrize() * rankCounts.get(rank);
		}

		return (double) total / payment;
	}

}
