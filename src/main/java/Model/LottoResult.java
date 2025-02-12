package Model;

public enum LottoResult {

    FIFTH(5000, 0),
    FOURTH(50000, 0),
    THIRD(1500000, 0),
    SECOND(30000000, 0),
    FIRST(2000000000, 0);

    private final int price;
    private int count;

    private static final int LOTTO_ALL_CORRECT = 6;
    private static final int LOTTO_ONE_WRONG = 5;
    private static final int LOTTO_TWO_WRONG = 4;
    private static final int LOTTO_THREE_WRONG = 3;


    LottoResult(int price, int count) {
        this.price = price;
        this.count = count;
    }

    public static void addCount(int count, boolean isBonus) {
        if (count == LOTTO_ALL_CORRECT) {
            FIRST.count++;
        }
        if (count == LOTTO_ONE_WRONG && isBonus) {
            SECOND.count++;
        }
        if (count == LOTTO_ONE_WRONG && !isBonus) {
            THIRD.count++;
        }
        if (count == LOTTO_TWO_WRONG) {
            FOURTH.count++;
        }
        if (count == LOTTO_THREE_WRONG) {
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
