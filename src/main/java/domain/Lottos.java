package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private static List<Lotto> lottos = new ArrayList<>();

    public Lottos(){

    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void addLottos(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public int getDummySize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}