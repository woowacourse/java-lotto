package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Rank> match(WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            ranks.add(winningLotto.match(lotto));
        }
        return ranks;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }

    public Lottos add(Lottos another) {
        List<Lotto> copiedLottos = new ArrayList<>(lottos);
        copiedLottos.addAll(another.lottos);
        return new Lottos(copiedLottos);
    }
}
