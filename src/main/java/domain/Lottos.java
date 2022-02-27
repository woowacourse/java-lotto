package domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
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

    public Statistic getWinningStatistics(WinningNumber winningNumber, LottoNumber bonusBall) {
        EnumMap<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
        initRankCountMap(rankCountMap);

        for (Lotto lotto : lottos) {
            int matchCount = lotto.match(winningNumber);
            boolean hasBonusBall = lotto.hasBonusBall(bonusBall);
            Rank key = Rank.valueOf(matchCount, hasBonusBall);
            rankCountMap.put(key, rankCountMap.get(key) + 1);
        }
        return new Statistic(rankCountMap);
    }

    private void initRankCountMap(EnumMap<Rank, Integer> rankCountMap) {
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
