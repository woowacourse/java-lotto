package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(List<Lotto> manuals, int randomCount) {
        List<Lotto> lottos = Stream.concat(manuals.stream(), randomLottosStream(randomCount))
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public static Lottos ofRandom(int randomCount) {
        List<Lotto> lottos = randomLottosStream(randomCount)
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    private static Stream<Lotto> randomLottosStream(int randomCount) {
        return Stream.generate(Lotto::new)
                .limit(randomCount);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public String toString() {
        return "Lottos{" + "lottos=" + lottos + '}';
    }
}
