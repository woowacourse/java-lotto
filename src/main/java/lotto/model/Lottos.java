package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lottos {

    private List<Lotto> lottos;
    private Map<Rank, Integer> rankCount;

    public Lottos() {
        this.lottos = new ArrayList<>();
        rankCount = Rank.initMap();
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

    public void countRank() {
        lottos.forEach(lotto -> {
            rankCount.put(lotto.getRank(), rankCount.get(lotto.getRank()) + 1);
        });
    }
}
