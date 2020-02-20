package domain;

public class Profit {

    public static final int LOTTO_PRICE = 1000;

    public int calculateProfitRatio(int totalProfit, int lottoCount) {
        return totalProfit / (LOTTO_PRICE * lottoCount);
    }
}
