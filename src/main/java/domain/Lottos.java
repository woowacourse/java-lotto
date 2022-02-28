package domain;

import java.util.List;

public class Lottos {

    List<Lotto> lottos;

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


}
