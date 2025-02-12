package Model;

public enum LottoResult {
    FIFTH(5000, 0),
    FOURTH(50000, 0),
    THIRD(1500000, 0),
    SECOND(30000000, 0),
    FIRST(2000000000, 0);

    private final int price;
    private int count;

    LottoResult(int price, int count) {
        this.price = price;
        this.count = count;
    }

    public static void addCount(int count, boolean isBonus) {
        if (count == 6) {
            FIRST.count++;
        }
        if (count == 5 && isBonus) {
            SECOND.count++;
        }
        if (count == 5 && !isBonus) {
            THIRD.count++;
        }
        if (count == 4) {
            FOURTH.count++;
        }
        if (count == 3) {
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

    public int getCount() {
        return count;
    }
}
