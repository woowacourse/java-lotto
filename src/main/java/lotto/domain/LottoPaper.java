package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoPaper {
    private List<Lotto> lottos;

    LottoPaper(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    List<Rank> matchLotto(WinningLotto winningLotto) {
        List<Rank> result = new ArrayList<>();
        lottos.forEach(lotto -> result.add(winningLotto.match(lotto)));
        return result;
    }
}
