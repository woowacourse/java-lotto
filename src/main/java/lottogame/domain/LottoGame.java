package lottogame.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lottogame.domain.machine.LottoTicketIssueMachine;
import lottogame.domain.number.LottoWinningNumbers;
import lottogame.domain.ticket.LottoTickets;

public class LottoGame {

    private static final int INIT_COUNT = 0;

    private final LottoTicketIssueMachine lottoTicketIssueMachine;


    public LottoGame(final LottoTicketIssueMachine lottoTicketIssueMachine) {
        this.lottoTicketIssueMachine = lottoTicketIssueMachine;
    }

    public LottoTickets issueManualTickets(final List<Set<Integer>> manualTicketNumbers) {
        return this.lottoTicketIssueMachine.issueManualTickets(manualTicketNumbers);
    }

    public LottoTickets issueAutoTickets() {
        return this.lottoTicketIssueMachine.issueAutoTickets();
    }

    public Map<Rank, Integer> getMatchingResult(final LottoTickets lottoTickets, final LottoWinningNumbers lottoWinningNumbers) {
        Map<Rank, Integer> ranks = new EnumMap<>(
            lottoTickets.getMatchingResult(lottoWinningNumbers, initMatchingResults())
        );
        ranks.remove(Rank.FAIL);
        return ranks;
    }

    private Map<Rank, Integer> initMatchingResults() {
        Map<Rank, Integer> matchingResults = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            matchingResults.put(rank, INIT_COUNT);
        }
        return matchingResults;
    }

    public double getYield(final Map<Rank, Integer> ranks) {
        return totalWinningPrice(ranks) / totalInvestment(ranks);
    }

    private double totalInvestment(final Map<Rank, Integer> ranks) {
        return countBoughtTickets(ranks) * LottoTicketIssueMachine.getTicketPrice();
    }

    private int countBoughtTickets(final Map<Rank, Integer> ranks) {
        return ranks.values()
            .stream()
            .reduce(0, Integer::sum);
    }

    private double totalWinningPrice(final Map<Rank, Integer> ranks) {
        return ranks.entrySet()
            .stream()
            .mapToInt(rank -> multiplyPriceByCount(rank.getKey(), rank.getValue()))
            .sum();
    }

    private int multiplyPriceByCount(Rank rank, Integer count) {
        return rank.getPrice() * count;
    }
}
