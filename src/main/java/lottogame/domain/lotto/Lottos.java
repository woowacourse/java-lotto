package lottogame.domain.lotto;

import lottogame.domain.stats.Rank;
import lottogame.domain.stats.LottoResults;

import java.util.*;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> values) {
        lottos.addAll(values);
    }

    public List<Lotto> values() {
        return new ArrayList<>(lottos);
    }

    public LottoResults matchLottos(WinningLotto winningLotto) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values()).forEach(rank -> results.put(rank, 0));
        for (Lotto lotto : lottos) {
            int count = lotto.match(winningLotto.values());
            boolean bonus = lotto.containsBonus(winningLotto);
            Rank rank = Rank.of(count, bonus);
            results.put(rank, results.get(rank) + 1);
        }
        return new LottoResults(results);
    }
}
