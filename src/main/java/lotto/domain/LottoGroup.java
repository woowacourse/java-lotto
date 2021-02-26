package lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoGroup {

    private final List<Lotto> lottos;

    public LottoGroup(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> lottoGroup() {
        return Collections.unmodifiableList(lottos);
    }
}
