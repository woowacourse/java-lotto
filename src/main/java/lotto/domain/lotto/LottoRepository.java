package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoRepository {
    private final List<Lotto> lottos;

    public LottoRepository() {
        this.lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public void addAll(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
