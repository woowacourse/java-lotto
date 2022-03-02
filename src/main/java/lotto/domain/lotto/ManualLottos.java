package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
public class ManualLottos {

    private List<Lotto> lottos = new ArrayList<>();

    public void add(final Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
