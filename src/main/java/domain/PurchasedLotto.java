package domain;

import domain.strategy.PurchaseStrategy;
import domain.strategy.RandomPurchaseStrategy;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLotto {

    private static final int LOTTO_PRICE = 1000;

    private List<Lotto> lottos = new ArrayList<>();
    private PrizeResult finalResult = new PrizeResult(); // 이것도 prizeResult로 변경은 어떨까
    private int inputMoney;

    public PurchasedLotto(int inputMoney) {
        this.inputMoney = inputMoney;
        purchaseMaximunLottos();
    }

    private void purchaseMaximunLottos() {
        for (int i = 0; i < inputMoney / LOTTO_PRICE; i++) {
            purchase(new RandomPurchaseStrategy());
        }
    }

    public void purchase(PurchaseStrategy purchaseStrategy) { // private로 변경 -> test코드는?
        Lotto lotto = new Lotto(purchaseStrategy.getNumbers());
        lottos.add(lotto);
    }

    public PrizeResult calculateWinning(WinningNumber winningNumber) { // 당첨 계산? -> calculatePrizeResult는 어떨까
        lottos.stream()
                .forEach(lotto -> {
                    Prize winnerPrice = lotto.calculateRank(winningNumber);
                    finalResult.updatePrizeResult(winnerPrice);
                });
        return finalResult;
    }

    public float calculateEarningRate() {
        long totalPrice = finalResult.totalPrize(); // totalPrize로 변경

        float earningRate = (float) totalPrice / inputMoney;
        return (float) (Math.floor(earningRate * 100) / 100.0);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public PrizeResult getFinalResult() {
        return finalResult;
    }

}
