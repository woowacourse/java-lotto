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

    private final List<Lotto> lottos;

    public Lottos(final Money money) throws RuntimeException {
        this.lottos = new ArrayList<>();
        insertLottoToLottos(money.getBuyingLottoCount());
    }

    private void insertLottoToLottos(final int countLotto) throws RuntimeException {
        for (int i = 0; i < countLotto; i++) {
            insert(new Lotto(new AutoLottoNumbersGenerator()));
        }
    }

    public void insert(final Lotto lotto) {
        lottos.add(lotto);
    }

    public Map<Rank, Integer> calculateRanks(final LottoNumbers winningNumbers, final LottoNumber bonusNumber) {
        Map<Rank, Integer> rankCount = initMap();
        lottos.forEach(lotto -> {
            Rank rank = lotto.calculateRank(winningNumbers, bonusNumber);
            rankCount.put(rank, rankCount.get(rank) + 1);
        });
        return rankCount;
    }

    private Map<Rank, Integer> initMap() {
        Map<Rank, Integer> map = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> map.put(rank, 0));
        return map;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public int size() {
        return lottos.size();
    }
}
