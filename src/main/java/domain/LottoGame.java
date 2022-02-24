package domain;

import java.util.Arrays;
import java.util.List;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;

    private Lottos lottos;

    public LottoGame() {
    }

    public LottoGame(List<Lotto> lottos) {
        this.lottos = new Lottos(lottos);
    }

    public Lottos buyLotto(Money money) {
        int lottoAmount = money.money() / LOTTO_PRICE;
        lottos = new Lottos(lottoAmount);
        return lottos;
    }

    public void makeResult(List<Integer> winningNumbers, Integer bonusNumber) {
        lottos.compareAllLotto(winningNumbers, bonusNumber);
    }

    public double getYield() {
        if (lottos.getSize() == 0) {
            return 0;
        }

        int prize = Arrays.stream(Rewards.values())
                .map(Rewards::calculateYield)
                .reduce(0, Integer::sum);
        return (float) prize / (1000 * lottos.getSize());
    }

    public Lottos getLottos() {
        return lottos;
    }
}
