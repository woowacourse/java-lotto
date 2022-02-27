package domain;

import java.util.*;

public class Lottos {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.generateLottoNumbers(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
        return new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public Statistic getWinningStatistics(WinningLotto winningNumber) {
        Statistic statistic = Statistic.initStatistic();
        lottos.forEach(lotto -> {
            Rank rank = lotto.match(winningNumber);
            statistic.add(rank);
        });
        return statistic;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}