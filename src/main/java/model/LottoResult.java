package model;

public enum LottoResult {
    FIFTH(3, 5000, 0),
    FOURTH(4, 50000, 0),
    THIRD(5, 1500000, 0),
    SECOND(5, 30000000, 0),
    FIRST(6, 2000000000, 0);

    private final int targetCount;
    private final int price;
    private int count;

    LottoResult(int targetCount, int price, int count) {
        this.targetCount = targetCount;
        this.price = price;
        this.count = count;
    }

    public static void addCount(int count, boolean isBonus) {
        if (count == LottoResult.FIRST.targetCount) {
            FIRST.count++;
        }
        if (count == LottoResult.SECOND.targetCount && isBonus) {
            SECOND.count++;
        }
        if (count == LottoResult.THIRD.targetCount && !isBonus) {
            THIRD.count++;
        }
        if (count == LottoResult.FOURTH.targetCount) {
            FOURTH.count++;
        }
        if (count == LottoResult.FIFTH.targetCount) {
            FIFTH.count++;
        }
    }

    public static double lottoRateOfReturn(int price) {
        double result = (double) calculateWinnings() / price;
        result = Math.floor(result * 100);
        result /= 100;
        return result;
    }

    private static int calculateWinnings() {
        int result = 0;
        for (LottoResult lottoResult : LottoResult.values()) {
            result += lottoResult.price * lottoResult.count;
        }
        return result;
    }

    public int getCount() {
        return count;
    }
}
