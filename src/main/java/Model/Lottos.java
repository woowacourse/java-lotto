package Model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(int count) {
        this.lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
