package lottogame.domain.lotto;

import lottogame.domain.stats.Rank;

import java.util.*;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> values() {
        return new ArrayList<>(lottos);
    }

    public Map<Rank, Integer> matchLottos(WinningLotto winningLotto) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values()).forEach(rank -> results.put(rank, 0));
        for (Lotto lotto : lottos) {
            int count = lotto.match(winningLotto.lotto());
            boolean bonus = lotto.contains(winningLotto.bonusNumber());
            Rank rank = Rank.of(count, bonus);
            results.put(rank, results.get(rank) + 1);
        }
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
