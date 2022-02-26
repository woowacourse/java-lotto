package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;
    private final int manuals;
    private final int randoms;

    private Lottos(List<Lotto> lottos, int manuals, int randoms) {
        this.lottos = lottos;
        this.manuals = manuals;
        this.randoms = randoms;
    }

    public static Lottos of(List<Lotto> manuals, int randomCount) {
        List<Lotto> lottos = Stream.concat(manuals.stream(), randomLottosStream(randomCount))
                .collect(Collectors.toList());

        return new Lottos(lottos, manuals.size(), randomCount);
    }

    public static Lottos ofRandom(int randomCount) {
        List<Lotto> lottos = randomLottosStream(randomCount)
                .collect(Collectors.toList());

        return new Lottos(lottos, 0, randomCount);
    }

    private static Stream<Lotto> randomLottosStream(int randomCount) {
        return Stream.generate(Lotto::new)
                .limit(randomCount);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getManuals() {
        return manuals;
    }

    public int getRandoms() {
        return randoms;
    }

    @Override
    public String toString() {
        return "Lottos{" + "lottos=" + lottos + '}';
    }
}
