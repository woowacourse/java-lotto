package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lotto.model.generator.AutoLottoNumbersGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class Lottos {

    private static final int PRICE_PER_LOTTO = 1000;

    private final List<Lotto> lottos;
    private final Map<Rank, Integer> rankCount;

    public Lottos(final Money money) throws RuntimeException {
        this.lottos = new ArrayList<>();
        insertLottoToLottos(money.getBuyingLottoCount());
        rankCount = initMap();
    }

    private void insertLottoToLottos(final int countLotto) throws RuntimeException {
        for (int i = 0; i < countLotto; i++) {
            insert(new Lotto(new AutoLottoNumbersGenerator()));
        }
    }

    public void insert(final Lotto lotto) {
        lottos.add(lotto);
    }

    private Map<Rank, Integer> initMap() {
        Map<Rank, Integer> map = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> map.put(rank, 0));
        return map;
    }

    public Map<Rank, Integer> calculateRanks(final LottoNumbers winningNumbers, final LottoNumber bonusNumber) {
        lottos.forEach(lotto -> lotto.calculateRank(winningNumbers, bonusNumber));
        countEachRank(winningNumbers, bonusNumber);
        Map<Rank, Integer> newRankCount = new LinkedHashMap<>(rankCount);
        return newRankCount;
    }

    private void countEachRank(final LottoNumbers winningNumbers, final LottoNumber bonusNumber) {
        lottos.forEach(lotto -> {
            Rank rank = lotto.calculateRank(winningNumbers, bonusNumber);
            rankCount.put(rank, rankCount.get(rank) + 1);
        });
    }

    public List<Lotto> getLottos() {
        List<Lotto> newLottos = new ArrayList<>(lottos);
        return newLottos;
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

    public Integer getEachRankCount(final Rank rank) {
        return rankCount.get(rank);
    }
}
