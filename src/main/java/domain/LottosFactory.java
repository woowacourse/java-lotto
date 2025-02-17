package domain;

import domain.generator.Generator;
import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    private final Generator generator;

    public LottosFactory(final Generator generator) {
        this.generator = generator;
    }

    public Lottos from(final int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i<count; i++) {
            lottos.add(Lotto.from(generator.generate()));
        }

        return new Lottos(lottos);
    }

}
