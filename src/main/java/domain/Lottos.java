package domain;

import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void rankAll(WinningLotto winningLotto) {
        lottos.forEach(lotto -> lotto.rankTier(winningLotto));
    }

    public int countTiers(PrizeTier prizeTier) {
        return (int) lottos.stream()
            .filter(lotto -> lotto.isTierMatched(prizeTier))
            .count();
    }

    public long calculateTotalPrize() {
        return lottos.stream()
            .map(Lotto::getPrizeTier)
            .mapToInt(PrizeTier::getPrize)
            .sum();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
