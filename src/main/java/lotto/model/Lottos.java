package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lotto.util.RandomNumbersGenerator;

public class Lottos {

    private static final int PRICE_PER_LOTTO = 1000;

    private final List<Lotto> lottos;
    private final Map<Rank, Integer> rankCount;

    public Lottos(Money money) {
        this.lottos = new ArrayList<>();
        insertLottoToLottos(money.getBuyingLottoCount());
        rankCount = initMap();
    }

    private void insertLottoToLottos(int countLotto) {
        for (int i = 0; i < countLotto; i++) {
            insert(new Lotto(new RandomNumbersGenerator()));
        }
    }

    public void insert(Lotto lotto) {
        lottos.add(lotto);
    }

    private Map<Rank, Integer> initMap() {
        Map<Rank, Integer> map = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> map.put(rank, 0));
        return map;
    }

    public void calculateRanks(List<Integer> winningNumbers, BonusNumber bonusNumber) {
        lottos.forEach(lotto -> lotto.calculateRank(winningNumbers, bonusNumber));
    }

    public void countRank() {
        lottos.forEach(lotto -> {
            rankCount.put(lotto.getRank(), rankCount.get(lotto.getRank()) + 1);
        });
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Integer getCount(Rank rank) {
        return rankCount.get(rank);
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public double getRevenue() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrice() * getEachRankCount(rank);
        }
        return ((double) sum / (lottos.size() * PRICE_PER_LOTTO));
    }

    private Integer getEachRankCount(Rank rank) {
        return rankCount.get(rank);
    }
}
