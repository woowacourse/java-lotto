package domain;

public class LottoMachine {

    private final int money;
    private final LottoResult lottoResult;

    public LottoMachine(int money) {
        this.money = money;
        this.lottoResult = new LottoResult();
    }

    public double calculateProfit() {
        return Math.floor((lottoResult.sumTotalPrice() / (double) money)  * 100) / (double) 100;
    }

    public void putLotto(LottoRank rank) {
        this.lottoResult.putLottoRank(rank);
    }
}
