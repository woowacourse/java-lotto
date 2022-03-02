package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
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

    public static Lottos combineLottos(Lottos firstLottos, Lottos secondLottos) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(firstLottos.lottos);
        lottos.addAll(secondLottos.lottos);
        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int size() {
        return lottos.size();
    }
}
