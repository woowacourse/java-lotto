package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.exception.InvalidException;

public class Lottos {

    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(final int count) {
        checkCount(count);

        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
    }

    private static void checkCount(final int count) {
        if (count < 1) {
            throw new IllegalArgumentException(InvalidException.ERROR_WRONG_INPUT_MONEY);
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Lottos addLottos(List<Lotto> addLottos) {
        this.lottos.addAll(addLottos);
        return this;
    }
}
