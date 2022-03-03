package domain;

import java.util.List;

public class LottoGame {

    private List<Lotto> lottos;
    private PrizeResult prizeResult = new PrizeResult();

    public LottoGame(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public PrizeResult calculatePrizeResult(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            prizeResult.updatePrizeResult(winningLotto.calculatePrize(lotto));
        }
        return prizeResult;
    }

    public double calculateEarningRate() {
        long totalPrize = prizeResult.totalPrize();
        double earningRate = (double)totalPrize / (lottos.size() * Lotto.PRICE);
        return Math.floor(earningRate * 100) / 100.0;
    }

    public PrizeResult getPrizeResult() {
        return prizeResult;
    }

}
