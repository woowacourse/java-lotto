package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    //int lottoAmount;
    List<Lotto> lottos;

    public Lottos(int lottoAmount) {
        this.lottos = generateLottos(lottoAmount);
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    private List<Lotto> generateLottos(int lottoAmount) {
        lottos = new ArrayList<>();

        for (int i = 0; i < lottoAmount; ++i) {
            lottos.add(new Lotto());
        }

        return lottos;

    }

    public void compareAllLotto(List<Integer> winningNumbers, int bonusNumber) {
        for (int i = 0; i < lottos.size(); ++i) {
            Rewards rewards = lottos.get(i).checkWinning(winningNumbers, bonusNumber);
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
