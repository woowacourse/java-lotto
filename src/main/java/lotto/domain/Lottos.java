package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Prizes calculatePrize(WinningLotto winningLotto) {
        List<Prize> result = new ArrayList<>();
        for (Lotto lotto : lottos) {
            result.add(new Prize(winningLotto.matchCount(lotto), winningLotto.matchBonusNumber(lotto)));
        }
        return new Prizes(result);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
