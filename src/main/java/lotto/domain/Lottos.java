package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(int payment) {
        for (int i = 0; i < payment / Lotto.PRICE; i++) {
            lottos.add(new Lotto());
        }
    }

    public int getTicketCount() {
        return lottos.size();
    }
}
