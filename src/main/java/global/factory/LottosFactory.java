package global.factory;

import domain.Money;
import domain.Lotto;
import domain.Lottos;
import global.generator.Generator;
import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    private final LottoFactory lottoFactory;

    public LottosFactory(Generator generator) {
        this.lottoFactory = new LottoFactory(generator);
    }

    public Lottos from(final int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i<count; i++) {
            lottos.add(lottoFactory.create());
        }

        return new Lottos(lottos);
    }

}
