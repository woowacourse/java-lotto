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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
            stringBuilder.append(lotto.toString());
        }
        return stringBuilder.toString();
    }
}
