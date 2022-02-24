package service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.Ball;
import domain.Balls;
import domain.RandomTicketGenerator;
import domain.Rank;
import domain.Tickets;
import dto.AnalysisDto;

public class LottoService {
	private static final int LOTTO_PRICE = 1000;
	private static final long INIT_TOTAL = 0L;

	private Tickets tickets;
	private int payment;

	public void initPayment(int payment) {
		this.payment = payment;
	}

	public void generateTickets() {
		int ticketCount = this.payment / LOTTO_PRICE;
		this.tickets = new Tickets(ticketCount, new RandomTicketGenerator());
	}

	public Tickets getTickets() {
		return this.tickets;
	}

	public AnalysisDto generateAnalysis(List<Integer> answerNumbers, int bonusBall) {
		List<Rank> ranks = tickets.getRanks(new Balls(answerNumbers), new Ball(bonusBall));
		Map<Rank, Integer> rankCounts = getRankCount(ranks);
		double profitRate = getProfitRate(payment, rankCounts);

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
		long total = INIT_TOTAL;

		for (Rank rank : rankCounts.keySet()) {
			total += rank.getPrize() * rankCounts.get(rank);
		}

		return total / payment;
	}
}
