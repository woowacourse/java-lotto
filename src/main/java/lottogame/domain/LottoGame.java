package lottogame.domain;

import java.util.EnumMap;
import java.util.Map;
import lottogame.domain.machine.LottoTicketIssueMachine;
import lottogame.domain.number.LottoWinningNumbers;
import lottogame.domain.ticket.LottoTickets;

public class LottoGame {

    private static final int INIT_COUNT = 0;

    private final LottoTickets lottoTickets;
    private final LottoWinningNumbers lottoWinningNumbers;

    public LottoGame(final LottoTickets lottoTickets, final LottoWinningNumbers lottoWinningNumbers) {
        this.lottoTickets = lottoTickets;
        this.lottoWinningNumbers = lottoWinningNumbers;
    }

    public Map<Rank, Integer> getMatchingResult() {
        Map<Rank, Integer> ranks = new EnumMap<>(
            this.lottoTickets.getMatchingResult(this.lottoWinningNumbers, initMatchingResults())
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
