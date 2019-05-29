package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        if (lottos == null || lottos.contains(null)) {
            throw new NullArgumentException("생성자의 인자로 null 을 넘길 수 없습니다.");
        }
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
}
