package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public PrizeResult prizeResult(WinningNumbers winningNumber) {
        return new PrizeResult(lottos, winningNumber);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int amountOfLottos() {
        return lottos.size() * LOTTO_PRICE;
    }

}
