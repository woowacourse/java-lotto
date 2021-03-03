package domain.lottoGame;

import domain.dto.GameResult;
import domain.dto.PurchaseResult;
import domain.lottoGame.shuffleStrategy.DefaultShuffleStrategy;
import domain.Money;

public class LottoGame {
    private final static int PRICE = 1000;

    private Tickets tickets;
    private Lottos purchasedLottos;

    public LottoGame(Money amount) {
        tickets = new Tickets(amount.divide(PRICE));
        purchasedLottos = new Lottos();
    }

    public PurchaseResult purchaseLottos(Lottos manualLottos) {
        int numberOfManualLottos = manualLottos.getNumberOfLotto();
        purchaseManualLottos(manualLottos);

        int numberOfRandomLottos = tickets.left();
        purchaseRandomLottos(numberOfRandomLottos);

        return new PurchaseResult(purchasedLottos, numberOfManualLottos, numberOfRandomLottos);
    }

    private void purchaseManualLottos(Lottos lottos) {
        purchasedLottos = purchasedLottos.add(lottos);
        tickets = tickets.use(lottos.getNumberOfLotto());
    }

    private void purchaseRandomLottos(int numberOfRandomLottos) {
        Lottos randomLottos = LottoFactory.generates(new DefaultShuffleStrategy(), numberOfRandomLottos);
        purchasedLottos = purchasedLottos.add(randomLottos);
        tickets = tickets.use(numberOfRandomLottos);
    }

    public GameResult calculateResult(WinningLotto winningLotto) {
        LottoWinningTable winningTable = purchasedLottos.checkCorrect(winningLotto);
        return new GameResult(winningTable, findEarningRate(winningTable));
    }

    private double findEarningRate(LottoWinningTable winningTable) {
        Money revenue = winningTable.getTotalWinningMoney();
        Money used = new Money(purchasedLottos.getNumberOfLotto() * PRICE);

        return revenue.calculateEarningRate(used);
    }
}