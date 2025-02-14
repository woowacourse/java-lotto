package global.factory;

import domain.Amount;
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

    public Lottos from(final Amount amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; amount.compareAmount(i); i++) {
            lottos.add(lottoFactory.from());
        }

        return new Lottos(lottos);
    }

}
