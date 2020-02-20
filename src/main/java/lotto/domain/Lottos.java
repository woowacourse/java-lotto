package lotto.domain;

import java.util.List;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }

    public Lotto getLottoByIndex(int index) {
        if (index < 0 || index > lottos.size())
            throw new IllegalArgumentException("Lottos에 잘못된 접급입니다.");
        return this.lottos.get(index);
    }
}
