package model;

import constans.ErrorType;
import java.util.Map;

public class WinningResult {

    private final Map<LottoRank, Integer> lottoRanks;

    public WinningResult(final Map<LottoRank, Integer> lottoRanks) {
        validateRankSize(lottoRanks);
        this.lottoRanks = Map.copyOf(lottoRanks);
    }

    private void validateRankSize(final Map<LottoRank, Integer> lottoRanks) {
        if (lottoRanks.values()
            .stream()
            .anyMatch(count -> count < 0)) {
            throw new IllegalArgumentException(ErrorType.WINNING_RESULT_POSITIVE.getMessage());
        }
    }

    private long calculateRevenue() {
        long revenue = 0;
        for (final Map.Entry<LottoRank, Integer> entry : lottoRanks.entrySet()) {
            revenue += (long) entry.getKey()
                .getPrizeMoney() * entry.getValue();
        }

        return revenue;
    }

    private int calculateTotalLottoCount() {
        int totalLottoCount = 0;
        for (final Map.Entry<LottoRank, Integer> entry : lottoRanks.entrySet()) {
            totalLottoCount += entry.getValue();
        }

        return totalLottoCount;
    }

    private int calculateTotalLottoPrice() {
        return calculateTotalLottoCount() * Lotto.LOTTO_PRICE;
    }

    public boolean isDamage() {
        return calculateRateOfRevenue() < 1;
    }

    public double calculateRateOfRevenue() {
        final long revenue = calculateRevenue();
        final long totalLottoPrice = calculateTotalLottoPrice();

        return (double) revenue / totalLottoPrice;
    }

    public Map<LottoRank, Integer> getLottoRanks() {
        return Map.copyOf(lottoRanks);
    }
}
