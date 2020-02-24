package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private static final List<Lotto> lottos = new ArrayList<>();

    public Lottos(){ }

    public static void addLottos(List<Lotto> targetLottos) {
        lottos.addAll(targetLottos);
    }

    public static void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public static int getDummySize() {
        return lottos.size();
    }

    public static List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}