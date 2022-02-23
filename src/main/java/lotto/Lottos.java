package lotto;

import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateSize(lottos);
        this.lottos = lottos;
    }

    private void validateSize(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("구매한 로또 개수는 1개 이상이어야 한다.");
        }
    }
}
