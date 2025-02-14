package domain;

import java.util.EnumMap;

public class WinningResult {
    private final EnumMap<LottoPrize, Integer> result = new EnumMap<>(LottoPrize.class);

    public WinningResult() {
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            result.put(lottoPrize, 0);
        }
    }

    public void increaseCount(LottoPrize lottoPrize, final int count) {
        result.put(lottoPrize, result.get(lottoPrize) + count);
    }

    public int getCount(LottoPrize lottoPrize) {
        return result.get(lottoPrize);
    }

    public long getTotalPrices() {
        long totalPrices = 0;
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            totalPrices += (long) result.get(lottoPrize) * lottoPrize.getPrice();
        }
        return totalPrices;
    }

}
