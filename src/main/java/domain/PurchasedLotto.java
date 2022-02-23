package domain;

import domain.strategy.PurchaseStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PurchasedLotto {

    private List<Lotto> lottos = new ArrayList<>();
    private PrizeResult finalResult = new PrizeResult();
    private int inputMoney;

    public PurchasedLotto(int inputMoney) {
        this.inputMoney = inputMoney;
    }

    public void purchase(PurchaseStrategy purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.getNumbers());
        lottos.add(lotto);
    }

    public PrizeResult calculateWinning(Lotto winningLotto, LottoNumber bonus) {
        lottos.stream()
                .forEach(lotto -> {
                    WinnerPrice winnerPrice = lotto.calculateRank(winningLotto, bonus);
                    finalResult.updatePrizeResult(winnerPrice);
                });
        return finalResult;
    }



    public float calculateEarningRate() {
        long totalPrice = finalResult.totalPrize();

        float earningRate = (float) totalPrice / inputMoney;
        return (float) (Math.floor(earningRate * 100) / 100.0);
    }

}
