package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.prize.MatchResult;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public List<MatchResult> match(WinningLotto winningLotto) {
        return this.lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }
}
