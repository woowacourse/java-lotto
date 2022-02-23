package lotto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public void purchase(int count) {
        this.lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            this.lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }
}
