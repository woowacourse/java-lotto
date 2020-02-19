package domain.money;

public class LottoMoney {

    private static final int MINIMUM_COST = 1000;
    private static final int REMINDER = 0;
    private int lottoMoney;

    public LottoMoney(int lottoMoney) {
        if (lottoMoney < MINIMUM_COST) {
            throw new IllegalArgumentException();
        }
        if (lottoMoney % MINIMUM_COST != REMINDER) {
            throw new IllegalArgumentException();
        }
        this.lottoMoney = lottoMoney;
    }

    public int getLottoPurchaseCounts() {
        return lottoMoney / MINIMUM_COST;
    }
}
