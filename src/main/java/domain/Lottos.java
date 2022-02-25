package domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
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

    public Statistic getWinningStatistics(WinningNumber winningNumber) {
        Statistic statistic = Statistic.initStatistic();
        lottos.forEach(lotto -> {
            Rank rank = lotto.match(winningNumber);
            statistic.add(rank);
        });
        return statistic;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}