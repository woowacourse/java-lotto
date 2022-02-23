package domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.generateNumber());
        }
        return new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public Statistic getWinningStatistics(WinningNumber winningNumber, int bonusBall) {
        LinkedHashMap<Rank, Integer> statistics = initStatistics();
        lottos.forEach(lotto -> {
            int matchCount = lotto.match(winningNumber);
            boolean hasBonusBall = lotto.hasBonusBall(bonusBall);
            Rank key = Rank.valueOf(matchCount, hasBonusBall);
            statistics.put(key, statistics.get(key) + 1);
        });
        return new Statistic(statistics);
    }

    private LinkedHashMap<Rank, Integer> initStatistics() {
        LinkedHashMap<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> result.put(rank, 0));
        return result;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}