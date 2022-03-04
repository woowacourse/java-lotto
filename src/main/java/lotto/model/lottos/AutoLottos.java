package lotto.model.lottos;

import static lotto.ValidationUtils.*;

import java.util.List;

import lotto.model.Lotto;

public class AutoLottos {
    private final Lottos lottos;

    public AutoLottos(Lottos lottos) {
        validateEmptyCollection(lottos.getLottos());
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
