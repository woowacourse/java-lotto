package lotto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static final int PRICE = 1000;

    private List<Lotto> lottos;

    public void purchase(Money money) {
        this.lottos = new ArrayList<>();

        for (int i = 0; i < money.purchase(PRICE); i++) {
            this.lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }
}
