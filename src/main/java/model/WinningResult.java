package model;

import constans.ErrorType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WinningResult {

    private final Map<LottoRank, Integer> lottoRanks;

    public WinningResult(final Map<LottoRank, Integer> lottoRanks) {
        validateRankSize(lottoRanks);
        this.lottoRanks = Map.copyOf(lottoRanks);
    }

    private void validateRankSize(final Map<LottoRank, Integer> lottoRanks) {
        long negativeCount = lottoRanks.entrySet().stream()
                .mapToInt(Entry::getValue)
                .filter(count -> count < 0)
                .count();

        if (negativeCount > 0) {
            throw new IllegalArgumentException(ErrorType.WINNING_RESULT_POSITIVE.getMessage());
        }
    }

    public static WinningResult of(final List<Lotto> lottos, final WinningNumbers winningNumbers) {
        final Map<LottoRank, Integer> lottoRanks = new HashMap<>();

        for (final Lotto lotto : lottos) {
            final LottoRank lottoRank = LottoRank.of(lotto, winningNumbers);
            lottoRanks.put(lottoRank, lottoRanks.getOrDefault(lottoRank, 0) + 1);
        }

        return new WinningResult(lottoRanks);
    }

    private long calculateRevenue() {
        return lottoRanks.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    private int calculateTotalLottoCount() {
        return lottoRanks.entrySet().stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
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
