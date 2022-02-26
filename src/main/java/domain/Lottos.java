package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    // TODO: should be deleted
    public static Lottos ofRandom(int lottoCount) {
        List<Lotto> lottos = Stream.generate(Lotto::new)
                .limit(lottoCount)
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public void createAndAddLottos(int lottoCount) {
        Stream.generate(Lotto::new)
                .limit(lottoCount)
                .forEach(lottos::add);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public String toString() {
        return "Lottos{" + "lottos=" + lottos + '}';
    }
}
