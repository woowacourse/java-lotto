package domain.lottoGame;

import domain.lottoGame.shuffleStrategy.DefaultShuffleStrategy;
import domain.Money;

public class LottoGame {
    private final static int PRICE = 1000;

    public LottoGame() {
    }

    public int getNumberOfAvailablePurchases(Money amount) {
        return amount.divide(PRICE);
    }

    public Lottos purchaseLottos(int count) {
        return LottoFactory.generates(new DefaultShuffleStrategy(), count);
    }

    public LottoResult calculateResult(WinningLotto winningLotto, Lottos purchasedLottos) {
        LottoWinningTable winningTable = purchasedLottos.makeWinningTable(winningLotto);
        double earningRate = calculateEarningRate(winningTable, purchasedLottos);

        return new LottoResult(winningTable, earningRate);
    }

    private double calculateEarningRate(LottoWinningTable winningTable, Lottos purchasedLottos) {
        Money revenue = winningTable.getTotalWinningMoney();
        return revenue.calculateEarningRate(findUsedMoney(purchasedLottos));
    }

    private Money findUsedMoney(Lottos purchasedLottos) {
        return new Money(purchasedLottos.getNumberOfLotto() * PRICE);
    }
}