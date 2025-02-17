package domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public EnumMap<Rank, Integer> calculateWinningResult(final WinningLotto winningLotto) {
        return countMatchNumbers(winningLotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private EnumMap<Rank, Integer> countMatchNumbers(final WinningLotto winningLotto) {
        EnumMap<Rank, Integer> countRank = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            countRank.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank matchRank = winningLotto.countMatchNumbers(lotto);
            countRank.put(matchRank, countRank.get(matchRank) + 1);
        }

        return countRank;
    }

}
