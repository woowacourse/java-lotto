package Lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ranks {
    private Map<Rank, Long> ranks = new HashMap<>();

    public Ranks(WinningNumber winningNumber, Lottos lottos) {
        lottos.getLottos().stream()
                .map(t -> calculateSingleRank(t, winningNumber))
                .forEach(this::incrementRanksCountIfExist);
        putEmptyRanksZero();
    }

    private Rank calculateSingleRank(Lotto lotto, WinningNumber winningNumber) {
        int hitCount = winningNumber.countHit(lotto);
        boolean bonusNumberExist = winningNumber.hasBonusNumber(lotto);
        return Rank.getRank(hitCount, bonusNumberExist);
    }

    private void incrementRanksCountIfExist(Rank t) {
        if (ranks.containsKey(t)) {
            Long val = ranks.get(t);
            val++;
            ranks.put(t, val);
        }
        ranks.putIfAbsent(t, 1L);
    }

    private void putEmptyRanksZero() {
        for (Rank rank : Rank.values()) {
            ranks.putIfAbsent(rank, 0L);
        }
    }

    public long addAllRankReward() {
        return ranks.keySet()
                .stream()
                .filter(Objects::nonNull)
                .mapToLong(rank -> ranks.get(rank) * rank.getRankReward())
                .sum();
    }

    public Map<Rank, Long> getRanks() {
        return ranks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranks ranks1 = (Ranks) o;
        return Objects.equals(ranks, ranks1.ranks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranks);
    }
}
