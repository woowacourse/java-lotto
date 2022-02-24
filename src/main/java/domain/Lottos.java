package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto());
        }

        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public String toString() {
        return "Lottos{" + "lottos=" + lottos + '}';
    }
}
