package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos = new ArrayList<>();

    public void addLotto(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public PrizeResult prizeResult(WinningNumbers winningNumber) {
        return new PrizeResult(lottos, winningNumber);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int totalPurchasePrice() {
        return lottos.size() * LOTTO_PRICE;
    }

}
