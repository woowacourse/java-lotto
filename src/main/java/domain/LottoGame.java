package domain;

import java.util.Arrays;
import java.util.List;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;
    private static final int EMPTY = 0;
    private static final int NO_YIELD = 0;
    private static final int SUM_BASE = 0;

    private Lottos lottos;
    private LottoGenerator lottoGenerator = new RandomLottoGenerator();

    public LottoGame() {
    }

    public LottoGame(List<Lotto> lottos) {
        this.lottos = new Lottos(lottos);
    }

    public void buyLotto(Money money) {
        int lottoAmount = money.convertToAmount();
        lottos = new Lottos(lottoGenerator.generateLottos(lottoAmount));
    }

    public void makeResult(List<Integer> winningNumbers, Integer bonusNumber) {
        lottos.compareAllLotto(winningNumbers, bonusNumber);
    }

    public double getYield() {
        if (lottos.getSize() == EMPTY) {
            return NO_YIELD;
        }

        int prize = Arrays.stream(Rewards.values())
            .map(Rewards::calculateYield)
            .reduce(SUM_BASE, Integer::sum);
        return (float) prize / (LOTTO_PRICE * lottos.getSize());
    }

    public Lottos getLottos() {
        return lottos;
    }
}
