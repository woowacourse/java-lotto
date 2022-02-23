package lotto.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lotto.domain.ball.Ball;
import lotto.domain.ball.Balls;
import lotto.domain.credit.Credit;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.dto.AnalysisDto;

public class LottoService {

	private Tickets tickets;
	private Credit credit;

	public void initPayment(int payment) {
		this.credit = new Credit(payment);
	}

	public void generateTickets() {
		int ticketCount = credit.getQuotient();
		this.tickets = new Tickets(ticketCount, new RandomTicketGenerator());
	}

	public Tickets getTickets() {
		return this.tickets;
	}

	public AnalysisDto generateAnalysis(List<Integer> answerNumbers, int bonusBall) {
		List<Rank> ranks = tickets.getRanks(new Balls(answerNumbers), new Ball(bonusBall));
		Map<Rank, Integer> rankCounts = getRankCount(ranks);
		double profitRate = getProfitRate(credit.getMoney(), rankCounts);

		return new AnalysisDto(rankCounts, profitRate);
	}

	public Map<Rank, Integer> getRankCount(List<Rank> ranks) {
		Map<Rank, Integer> rankMap = new LinkedHashMap<>();

		for (Rank rank : Rank.values()) {
			int count = Collections.frequency(ranks, rank);
			rankMap.put(rank, count);
		}
		return rankMap;
	}

	public double getProfitRate(int payment, Map<Rank, Integer> rankCounts) {
		long total = 0L;

		for (Rank rank : rankCounts.keySet()) {
			total += rank.getPrize() * rankCounts.get(rank);
		}

		return (double) total / payment;
	}

}
