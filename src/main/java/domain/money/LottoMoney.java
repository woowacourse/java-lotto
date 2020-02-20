package domain.money;

public class LottoMoney {

    private static final int MINIMUM_COST = 1000;
    private static final int REMINDER = 0;
    private int lottoMoney;

    public LottoMoney(int lottoMoney) {
        if (lottoMoney < MINIMUM_COST) {
            throw new IllegalArgumentException("로또 최소 구매 금액은 1000원입니다.");
        }
        if (lottoMoney % MINIMUM_COST != REMINDER) {
            throw new IllegalArgumentException("로또 구매는 1000원 단위로만 가능합니다.");
        }
        this.lottoMoney = lottoMoney;
    }

    public int getLottoPurchaseCounts() {
        return lottoMoney / MINIMUM_COST;
    }

    public double getMoney() {
        return lottoMoney;
    }
}
