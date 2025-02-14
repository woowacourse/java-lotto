package domain;

import domain.dto.GetResultDto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public EnumMap<Rank, Integer> calculateResultOfWinning(WinningLotto winningLotto) {
        EnumMap<Rank, Integer> countRank = countMatchNumbers(winningLotto);
        return countRank;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private EnumMap<Rank, Integer> countMatchNumbers(WinningLotto winningLotto) {
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
