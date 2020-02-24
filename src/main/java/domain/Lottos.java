package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    public static final int HEAD_INDEX = 0;
    private static List<Lotto> lottos = new ArrayList<>();

    public Lottos(){ }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void addLottos(List<Lotto> lottos) {
        this.lottos.addAll(HEAD_INDEX, lottos);
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