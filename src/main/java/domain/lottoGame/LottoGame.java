package domain.lottoGame;

import domain.lottoGame.dto.PurchaseResult;
import domain.lottoGame.shuffleStrategy.DefaultShuffleStrategy;
import domain.Money;
import domain.lottoGame.dto.LottoResult;

public class LottoGame {
    private final static int PRICE = 1000;

    private Lottos purchasedLottos;

    public LottoGame() {
        purchasedLottos = new Lottos();
    }

    public int getNumberOfAvailablePurchases(Money amount) {
        return amount.divide(PRICE);
    }

    public PurchaseResult purchaseLottos(Lottos manualLottos, int tickets) {
        int numberOfManualLottos = manualLottos.getNumberOfLotto();
        purchaseManualLottos(manualLottos);

        int numberOfRandomLottos = tickets - numberOfManualLottos;
        purchaseRandomLottos(tickets);

        return new PurchaseResult(purchasedLottos, numberOfManualLottos, numberOfRandomLottos);
    }

    private void purchaseManualLottos(Lottos lottos) {
        purchasedLottos = purchasedLottos.add(lottos);
    }

    private void purchaseRandomLottos(int count) {
        purchasedLottos = LottoFactory.generates(new DefaultShuffleStrategy(), count);
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        LottoWinningTable winningTable = purchasedLottos.checkCorrect(winningLotto);
        return new LottoResult(winningTable, findEarningRate(winningTable));
    }

    private double findEarningRate(LottoWinningTable winningTable) {
        Money revenue = winningTable.getTotalWinningMoney();
        Money used = new Money(purchasedLottos.getNumberOfLotto() * PRICE);

        return revenue.calculateEarningRate(used);
    }
}