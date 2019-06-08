package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {
    private Map<Rank, Integer> results = new HashMap<>();

    public Result() {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public void calculateResult(Lottoes lottoes, WinningLotto winningLotto) {
        List<Rank> ranks = lottoes.getRanks(winningLotto);
        for (Rank rank : ranks) {
            putRank(rank);
        }
        results = results.entrySet().stream()
                .filter(r -> r.getKey() != Rank.NONE)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public int getMatchlottoCountPerRank(Rank rank) {
        return results.get(rank);
    }

    public void putRank(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }

    public double getRate(Money money) {
       return money.getRate(results);
    }
}
