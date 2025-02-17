package domain;

import domain.generator.Generator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LottosFactory {

    public Lottos from(final int count, final Generator generator) {
        List<Lotto> lottos = Stream.generate(generator::generate)
                .limit(count)
                .map(Lotto::from)
                .toList();

        return new Lottos(lottos);
    }
}
