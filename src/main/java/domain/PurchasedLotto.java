package domain;

import domain.strategy.PurchaseStrategy;
import domain.strategy.RandomPurchaseStrategy;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLotto {

    private static final int LOTTO_PRICE = 1000;

    private List<Lotto> lottos = new ArrayList<>();
    private PrizeResult prizeResult = new PrizeResult();
    private int inputMoney;

    public PurchasedLotto(int inputMoney) {
        this.inputMoney = inputMoney;
        purchaseMaximumLottos();
    }

    private void purchaseMaximumLottos() {
        for (int i = 0; i < inputMoney / LOTTO_PRICE; i++) {
            purchase(new RandomPurchaseStrategy());
        }
    }

    public void purchase(PurchaseStrategy purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.getNumbers());
        lottos.add(lotto);
    }

    public PrizeResult calculatePrizeResult(WinningLotto winningLotto) {
        lottos.forEach(lotto -> {
                    Prize winnerPrice = lotto.calculateRank(winningLotto);
                    prizeResult.updatePrizeResult(winnerPrice);
                });
        return prizeResult;
    }

    public float calculateEarningRate() {
        long totalPrize = prizeResult.totalPrize();

        float earningRate = (float) totalPrize / inputMoney;
        return (float) (Math.floor(earningRate * 100) / 100.0);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public PrizeResult getPrizeResult() {
        return prizeResult;
    }

}
