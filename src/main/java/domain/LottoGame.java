package domain;

public class LottoGame {

    public LottoGame() {
    }

    public Lottos purchaseLottos(Money amount) {
        int lottoCount = Lotto.getNumberOfAvailablePurchases(amount);
        return LottoFactory.generates(new DefaultShuffleStrategy(), lottoCount);
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
        return new Money(purchasedLottos.getNumberOfLotto() * Lotto.PRICE);
    }
}