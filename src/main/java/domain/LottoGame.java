package domain;

import domain.strategy.PurchaseStrategy;
import domain.strategy.RandomPurchaseStrategy;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;

    private List<Lotto> lottos = new ArrayList<>();
    private PrizeResult prizeResult = new PrizeResult();

    public LottoGame(int inputMoney) {
        purchaseMaximumLottos(inputMoney);
    }

    private void purchaseMaximumLottos(int money) {
        for (int i = 0; i < money / LOTTO_PRICE; i++) {
            purchase(new RandomPurchaseStrategy());
        }
    }

    public void purchase(PurchaseStrategy purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.getNumbers());
        lottos.add(lotto);
    }

    public PrizeResult calculatePrizeResult(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            prizeResult.updatePrizeResult(winningLotto.calculatePrize(lotto));
        }
        return prizeResult;
    }

    public float calculateEarningRate() {
        long totalPrize = prizeResult.totalPrize();

        float earningRate = (float) totalPrize / lottos.size();
        return (float) (Math.floor(earningRate * 100) / 100.0);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public PrizeResult getPrizeResult() {
        return prizeResult;
    }

}
