package model;

import constans.ErrorType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningResult {

    private final Map<LottoRank, Integer> lottoRanks;

    public WinningResult(final Map<LottoRank, Integer> lottoRanks) {
        validateRankSize(lottoRanks);
        this.lottoRanks = Map.copyOf(lottoRanks);
    }


    private void validateRankSize(final Map<LottoRank, Integer> lottoRanks) {
        lottoRanks.forEach((lottoRank, integer) -> {
            if (integer < 0) {
                throw new IllegalArgumentException(ErrorType.WINNING_RESULT_POSITIVE.getMessage());
            }
        });
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
        long revenue = 0;
        for (final Map.Entry<LottoRank, Integer> entry : lottoRanks.entrySet()) {
            revenue += entry.getKey().getPrizeMoney() * entry.getValue();
        }
        return revenue;
    }

    public int calculateLottoRankCount(final LottoRank lottoRank) {
        return this.lottoRanks.getOrDefault(lottoRank, 0);
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
