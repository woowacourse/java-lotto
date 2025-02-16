package Model;

public enum LottoResult {

    FIFTH(5000, 0, 3),
    FOURTH(50000, 0, 4 ),
    THIRD(1500000, 0, 5),
    SECOND(30000000, 0, 5),
    FIRST(2000000000, 0, 6);

    private final int price;
    private int count;
    private final int correctNumber;

    LottoResult(int price, int count, int correctNumber) {
        this.price = price;
        this.count = count;
        this.correctNumber = correctNumber;
    }

    public static void addCount(int count, boolean isBonus) {
        if (count == LottoResult.FIRST.correctNumber) {
            FIRST.count++;
        }
        if (count == LottoResult.SECOND.correctNumber && isBonus) {
            SECOND.count++;
        }
        if (count == LottoResult.THIRD.correctNumber && !isBonus) {
            THIRD.count++;
        }
        if (count == LottoResult.FOURTH.correctNumber) {
            FOURTH.count++;
        }
        if (count == LottoResult.FIFTH.correctNumber) {
            FIFTH.count++;
        }
    }

    public static int calculateWinnings() {
        int result = 0;
        for (LottoResult lottoResult : LottoResult.values()) {
            result += lottoResult.price * lottoResult.count;
        }
        return result;
    }

    public static double lottoRateOfReturn(int price) {
        double result = (double) calculateWinnings() / price;
        result = Math.floor(result * 100);
        result /= 100;
        return result;
    }

    public int getCount() {
        return count;
    }
}
