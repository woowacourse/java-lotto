package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    List<Lotto> lottos;

    public Lottos(int lottoAmount) {
        this.lottos = generateLottos(lottoAmount);
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void compareAllLotto(List<Integer> winningNumbers, int bonusNumber) {
        for (Lotto lotto : lottos) {
            Rewards rewards = lotto.checkWinning(winningNumbers, bonusNumber);
            Rewards.addCount(rewards);
        }
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> generateLottos(int lottoAmount) {
        lottos = new ArrayList<>();

        for (int i = 0; i < lottoAmount; ++i) {
            lottos.add(new Lotto());
        }
        return lottos;
    }
}
