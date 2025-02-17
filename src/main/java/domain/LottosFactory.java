package domain;

import domain.generator.Generator;
import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    public Lottos from(final int count, final Generator generator) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i<count; i++) {
            lottos.add(Lotto.from(generator.generate()));
        }

        return new Lottos(lottos);
    }

}
