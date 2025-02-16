package domain;

import domain.dto.LottoResponse;
import domain.dto.LottosResponse;
import domain.dto.ResultResponse;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public EnumMap<Rank, Integer> countMatchNumbers(WinningLotto winningLotto) {
        EnumMap<Rank, Integer> countRank = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            countRank.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank matchRank = lotto.countMatchNumbers(winningLotto);
            countRank.put(matchRank, countRank.get(matchRank) + 1);
        }

        return countRank;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
