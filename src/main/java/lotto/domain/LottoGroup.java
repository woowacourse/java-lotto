package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGroup {

    private final List<Lotto> lottos;

    public LottoGroup() {
        this.lottos = new ArrayList<>();
    }

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> lottoGroup() {
        return Collections.unmodifiableList(lottos);
    }
}
