package lotto.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.LottoRank;
import lotto.domain.Money;

public class LottoStatisticsResponse {

    private final List<LottoWinningResponse> winningResponses;
    private final double profitRate;

    public LottoStatisticsResponse(List<LottoRank> ranks, Money money) {
        this.winningResponses = toWinningResponses(ranks);
        this.profitRate = calculateProfit(money);
    }

    private List<LottoWinningResponse> toWinningResponses(List<LottoRank> ranks) {
        Map<LottoRank, Integer> frequencyMap = countFrequency(ranks);
        return frequencyMap.entrySet()
            .stream()
            .map(LottoWinningResponse::from)
            .collect(Collectors.toList());
    }

    private Map<LottoRank, Integer> countFrequency(List<LottoRank> ranks) {
        return ignoreFailedRank().stream()
            .collect(Collectors.toMap(
                rank -> rank,
                rank -> Collections.frequency(ranks, rank))
            );
    }

    private List<LottoRank> ignoreFailedRank() {
        return Arrays.stream(LottoRank.values())
            .filter(x -> x != LottoRank.SIXTH)
            .collect(Collectors.toList());
    }

    private double calculateProfit(Money money) {
        long sum = winningResponses.stream()
            .mapToLong(this::calculateProfitPerWinning)
            .sum();
        return (double)sum / money.getAmount();
    }

    private long calculateProfitPerWinning(LottoWinningResponse response) {
        return (long)response.getPrize() * response.getTicketCount();
    }

    public List<LottoWinningResponse> getWinningResponses() {
        return winningResponses;
    }

    public double getProfitRate() {
        return profitRate;
    }

}
