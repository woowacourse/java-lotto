package lotto;

import java.util.LinkedHashMap;
import java.util.Map;

public class DashBoard {

    private final Map<Rank, Integer> ranks;

    public DashBoard() {
        ranks = initRanks();
    }

    private Map<Rank, Integer> initRanks() {
        Map<Rank, Integer> ranks = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
        return ranks;
    }

    public void update(Lotto lotto, Lotto winningLotto, int bonusNumber) {
        Rank rank = calculateRank(lotto, winningLotto, bonusNumber);
        ranks.put(rank, addCount(rank));
    }

    private Rank calculateRank(Lotto lotto, Lotto winningLotto, int bonusNumber) {
        int matchCount = lotto.getMatchCount(winningLotto);
        boolean isBonusMatch = lotto.contains(bonusNumber);

        return Rank.calculate(matchCount, isBonusMatch);
    }

    private int addCount(Rank rank) {
        return ranks.getOrDefault(rank, 0) + 1;
    }

    public Map<Rank, Integer> getRanks() {
        return Map.copyOf(ranks);
    }
}
