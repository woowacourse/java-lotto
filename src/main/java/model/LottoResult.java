package model;

public enum LottoResult {

    FIFTH(5000, 0, 3, false),
    FOURTH(50000, 0, 4, true ),
    THIRD(1500000, 0, 5, false),
    SECOND(30000000, 0, 5, false),
    FIRST(2000000000, 0, 6, false);

    private final int price;
    private int count;
    private final int correctNumber;
    private final boolean bonusBall;

    LottoResult(int price, int count, int correctNumber, boolean bonusBall) {
        this.price = price;
        this.count = count;
        this.correctNumber = correctNumber;
        this.bonusBall = bonusBall;
    }

    public static void addCount(int count, boolean isBonus) {
        for(LottoResult lottoResult : LottoResult.values()){
            isMatchWithBonus(count, isBonus, lottoResult);
        }
    }

    private static void isMatchWithBonus(int count, boolean isBonus, LottoResult lottoResult) {
        if (lottoResult.correctNumber == count && lottoResult.bonusBall == isBonus){
            lottoResult.count++;
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
