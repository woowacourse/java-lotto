package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(int count) {
        lottos = buyLotto(count);
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static List<Lotto> buyLotto(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoGenerator.make()));
        }
        return lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
