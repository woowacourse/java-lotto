package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
        validateSize(lottos);
    }

    public List<Rank> match(WinnerLotto winnerLotto) {
        return lottos.stream()
            .map(winnerLotto::findRank)
            .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    private void validateSize(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("구매한 로또 개수는 1개 이상이어야 한다.");
        }
    }
}
