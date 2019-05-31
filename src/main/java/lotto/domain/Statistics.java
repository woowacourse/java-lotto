package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistics {
    private Map<Rank, Integer> map = new HashMap<>();

    public Statistics() {
        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }
    }

    public void calculateResult(Lottoes lottoes, WinningLotto winningLotto) {
        List<Rank> ranks = lottoes.getRanks(winningLotto);
        for (Rank rank : ranks) {
            putRank(rank);
        }
        map = map.entrySet().stream()
                .filter(r -> r.getKey() != Rank.NONE)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public int getMatchlottoCountPerRank(Rank rank) {
        return map.get(rank);
    }

    public void putRank(Rank rank) {
        map.put(rank, map.get(rank) + 1);
    }

    public int getRate(Money money) {
       return money.getRate(map);
    }
}
