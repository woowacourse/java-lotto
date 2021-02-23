package lottogame.domain;

import java.util.EnumMap;
import java.util.Map;
import lottogame.domain.machine.LottoTicketIssueMachine;

public class LottoGameResult {

    private final Map<Rank, Integer> matchingResults;

    public LottoGameResult(Map<Rank, Integer> matchingResults) {
        this.matchingResults = new EnumMap<>(matchingResults);
    }

    public Map<Rank, Integer> getRanks() {
        Map<Rank, Integer> ranks = new EnumMap<>(this.matchingResults);
        ranks.remove(Rank.FAIL);
        return ranks;
    }

    public double totalInvestment() {
        return countBoughtTickets() * LottoTicketIssueMachine.getTicketPrice();
    }

    private int countBoughtTickets() {
        return this.matchingResults.values()
            .stream()
            .reduce(0, Integer::sum);
    }

    public double totalWinningPrice() {
        return this.matchingResults.entrySet()
            .stream()
            .mapToInt(rank -> multiplyPriceByCount(rank.getKey(), rank.getValue()))
            .sum();
    }

    private int multiplyPriceByCount(Rank rank, Integer count) {
        return rank.getPrice() * count;
    }
}
