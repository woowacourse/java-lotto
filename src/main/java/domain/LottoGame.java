package domain;

import java.util.Arrays;
import java.util.List;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;
    private static final int EMPTY = 0;
    private static final int NO_YIELD = 0;
    private static final int SUM_BASE = 0;

    private List<Integer> winningLottoNumbers;
    private int bonusNumber;
    private Lottos lottos;

    public LottoGame() {
    }

    public LottoGame(List<Lotto> lottos) {
        this.lottos = new Lottos(lottos);
    }

    public Lottos buyLotto(Money money) {
        int lottoAmount = money.money() / LOTTO_PRICE;
        lottos = Lottos.buyLottos(lottoAmount);
        return lottos;
    }

    public void enterWinningLottoNumbersAndBonusNumber(List<Integer> winningLottoNumbers, int bonusNumber) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
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
