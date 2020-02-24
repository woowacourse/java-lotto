package lotto.domain.result;

import lotto.domain.result.rank.Rank;

import java.util.Map;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class OverallResult {
    final Map<Rank, Integer> overallResult;

    public OverallResult(Map<Rank, Integer> overallResult) {
        this.overallResult = overallResult;
    }

    public double calculateWinRate() {
        return calculateTotalPrize() / calculateBettingMoney();
    }

    private double calculateTotalPrize() {
        return overallResult.keySet().stream()
                .mapToDouble(x -> x.getPrize() * overallResult.get(x))
                .sum();
    }

    private int calculateBettingMoney() {
        return overallResult.keySet().stream()
                .mapToInt(overallResult::get)
                .sum() * LOTTO_PRICE;
    }
}
