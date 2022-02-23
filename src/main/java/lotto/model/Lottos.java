package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public void insert(Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getCount() {
        return lottos.size();
    }

    public void calculateRanks(List<Integer> numbers, int bonusNumber) {
        lottos.forEach(lotto -> lotto.calculateRank(numbers, bonusNumber));
    }
}
