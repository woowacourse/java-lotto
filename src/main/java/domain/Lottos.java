package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public Lottos(final LottoCount lottoCount) {
        LottoMachine lottoMachine = new LottoMachine(lottoCount);
        lottos = lottoMachine.getLottos();
    }

    public void addLotto(final Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}