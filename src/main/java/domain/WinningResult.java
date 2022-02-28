package domain;

import constants.LottoConstants;
import java.util.HashMap;
import java.util.Map;

public class WinningResult {
    public static final double ROUND_UNIT = 100.0;

    private final Map<Rank, WinningCount> winningResult;
    private final LottoQuantity purchasedLottoQuantity;

    private WinningResult(Builder builder) {
        this.winningResult = builder.winningResult;
        this.purchasedLottoQuantity = builder.purchasedLottoQuantity;
    }

    public double getProfitRatio() {
        double purchaseMoney = purchasedLottoQuantity.getLottoQuantity() * LottoConstants.SINGLE_LOTTO_PRICE;
        return roundToSecondDigit(calculateTotalPrize() / purchaseMoney);
    }

    private long calculateTotalPrize() {
        return winningResult.entrySet()
                .stream()
                .map((entrySet) -> entrySet.getKey().getPrize() * entrySet.getValue().getCount())
                .reduce(0L, Long::sum);
    }

    private double roundToSecondDigit(double number) {
        return Math.round(number * ROUND_UNIT) / ROUND_UNIT;
    }

    public Map<Rank, WinningCount> getWinningResult() {
        return winningResult;
    }

    public WinningCount getWinningCountByRank(Rank rank) {
        return winningResult.get(rank);
    }

    public static class Builder {
        private final Map<Rank, WinningCount> winningResult = new HashMap<>();
        private final LottoQuantity purchasedLottoQuantity;

        public Builder(LottoQuantity purchasedLottoQuantity) {
            for (Rank rank : Rank.values()) {
                winningResult.put(rank, new WinningCount(0));
            }
            this.purchasedLottoQuantity = purchasedLottoQuantity;
        }

        public Builder first(WinningCount winningCount) {
            winningResult.put(Rank.FIRST, winningCount);
            return this;
        }

        public Builder second(WinningCount winningCount) {
            winningResult.put(Rank.SECOND, winningCount);
            return this;
        }

        public Builder third(WinningCount winningCount) {
            winningResult.put(Rank.THIRD, winningCount);
            return this;
        }

        public Builder fourth(WinningCount winningCount) {
            winningResult.put(Rank.FOURTH, winningCount);
            return this;
        }

        public Builder fifth(WinningCount winningCount) {
            winningResult.put(Rank.FIFTH, winningCount);
            return this;
        }

        public Builder noMatch(WinningCount winningCount) {
            winningResult.put(Rank.NO_MATCH, winningCount);
            return this;
        }

        public WinningResult build() {
            return new WinningResult(this);
        }
    }

    @Override
    public String toString() {
        return "WinningResult{" +
                "winningResult=" + winningResult +
                '}';
    }
}
