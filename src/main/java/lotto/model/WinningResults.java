package lotto.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningResults {
    private final WinningLotto winningLotto;
    private final Lottos lottos;
    private final List<WinningResult> results;

    public WinningResults(WinningLotto winningLotto, Lottos lottos) {
        this.winningLotto = winningLotto;
        this.lottos = lottos;
        this.results = new ArrayList<>();
    }

    public void calculateWinningResults() {
        Map<Rank, Integer> ranks = saveRanks();
        toResults(ranks);
    }

    private Map<Rank, Integer> saveRanks() {
        Map<Rank, Integer> ranks = initRanks();
        for (Lotto lotto : lottos.getLottos()) {
            int matchingCount = lotto.calculateMatchingCount(winningLotto.getWinningLotto());
            Rank findRank = Rank.findBy(matchingCount, lotto.hasNumber(winningLotto.getBonusNumber()));
            ranks.put(findRank, ranks.getOrDefault(findRank, 0) + 1);
        }
        return ranks;
    }

    private Map<Rank, Integer> initRanks() {
        Map<Rank, Integer> ranks = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
        return ranks;
    }

    private void toResults(final Map<Rank, Integer> ranks) {
        for (Rank rank : ranks.keySet()) {
            toResult(ranks, rank, results);
        }
    }

    private void toResult(final Map<Rank, Integer> ranks, final Rank rank,
                          final List<WinningResult> results) {
        if (rank.isNone()) {
            return;
        }
        if (rank.isSecond()) {
            saveResults(rank, true, ranks, results);
            return;
        }
        saveResults(rank, false, ranks, results);
    }

    private void saveResults(final Rank rank, final boolean hasBonus, final Map<Rank, Integer> ranks,
                             final List<WinningResult> results) {
        WinningResult winningResult = new WinningResult(rank.getMatchingCount(),
                rank.getWinningAmount(), hasBonus, ranks.get(rank));
        results.add(winningResult);
    }


    public long calculateEarnedMoney() {
        long totalEarnedMoney = 0L;
        for (WinningResult result : this.getResults()) {
            totalEarnedMoney += result.getWinningAmount() * result.getWinningCount();
        }
        return totalEarnedMoney;
    }

    public List<WinningResult> getResults() {
        return results;
    }
}
