package lotto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static final int PRICE = 1000;

    private List<Lotto> lottos;

    public void purchase(int amount) {
        this.lottos = new ArrayList<>();

        for (int i = 0; i < countTickets(amount); i++) {
            this.lottos.add(new Lotto());
        }
    }

    private int countTickets(int amount) {
        return amount / PRICE;
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }
}
